#!/bin/bash

# Get file name changes using git status
file_changes=$(git status --porcelain | awk '$1 == "M" || $1 == "A" {print $2}')

# Initialize an associative array to store full path of methods
fully_qualified_methods=()

cd ..

modified_method_name=""

#Get method name for range and file name
function get_method_name() {
    file=$1
    line_number=$2

    export file
    source bin/get-method-range.sh
    method_name_list=$method_name_list
    method_range_list=$method_range_list

    for ((i = 0; i < ${#method_range_list[@]}; i++)); do
        range=(${method_range_list[i]})
        start=${range[0]}
        end=${range[1]}

        if [[ "$line_number" -ge "$start" ]] && [[ "$line_number" -le "$end" ]]; then
            modified_method_name=${method_name_list[i]}
            break
        fi
    done
}


# Loop through the changed files
for file in $file_changes; do
    # Check if the file is a source code file in src/main/java/com/example
    if [[ $file == src/main/java/com/testvagrant/example/* ]]; then
        # Get the git diff for the file
        diff_output=$(git diff --unified=0 "$file")


        # Extract method names containing the changed lines
        while IFS= read -r line; do
            # Check if the line is a modified line
            if [[ $line =~ ^[@]{2}.*[+-]  ]]; then
                # Extract the line number
                line_number=$(echo "$line" | sed -n -E 's/^\@\@ [^+]*\+([0-9]+).*/\1/p')
                echo "line_number : $line_number"

                #Get method name
                get_method_name "$file" "$line_number"
                echo "modified_method_name : $modified_method_name"

                # Get full path of method from source root
                # Replace '/' with '.' in the file path
                class_path="${file//\//.}"
                # Remove the src.main.java. from prefix
                class_path="${class_path#src.main.java.}"
                # Construct the fully qualified method name
                fully_qualified_method="${class_path%.java}.${modified_method_name}"
                echo "fully_qualified_method: $fully_qualified_method"

                fully_qualified_methods+=($fully_qualified_method)
            fi
        done <<< "$diff_output"
    fi
done

# Construct JSON manually
json_data="{ \"key\": ["
first=true
for method in "${fully_qualified_methods[@]}"; do
    if [ "$first" = false ]; then
        json_data+=","
    fi
    json_data+="\"$method\""
    first=false
done
json_data+="]}"

cd bin

# Save the JSON string to a file (e.g., output.json)
echo "$json_data" > modified-methods.json

cd ..