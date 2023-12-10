#!/bin/bash

# Get file name changes using git status
file_changes=$(git status --porcelain | awk '$1 == "M" || $1 == "A" {print $2}')

# Initialize an associative array to store full path of methods
fully_qualified_methods=()

#declare -A class_method_map
#echo "class_method_map after declaration ${class_method_map[*] }"

cd ..

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

                  method_name=$(awk -v ln="$line_number" '
                    BEGIN { RS="{"; found=0 }
                    NR <= ln && /([a-zA-Z_][a-zA-Z_0-9<>]*)[[:space:]]*\([^)]*\)[[:space:]]*$/ {
                      method_name=$0; found=1; nextfile
                    }
                    END {
                      if (found) {
                        gsub(/^[[:space:]]+|[[:space:]]+$/, "", method_name);
                        print method_name;
                      }
                    }
                  ' "$file")
                # Print the method name
                echo "File: $file, Method: $method_name"


                # Get full path of method from source root
                # Extract the method name from the method signature
                method_name=$(echo "$method_name" | awk '{print $3}')
                # Replace '/' with '.' in the file path
                class_path="${file//\//.}"
                # Remove the src.main.java. from prefix
                class_path="${class_path#src.main.java.}"
                # Remove parameter types from the method signature
                method_name="${method_name%%(*}"
                # Construct the fully qualified method name
                fully_qualified_method="${class_path%.java}.${method_name}"

                fully_qualified_methods+=($fully_qualified_method)
            fi
        done <<< "$diff_output"
    fi
done


# Print the content of list
for method in "${fully_qualified_methods[@]}"; do
    echo "Methods Changed : $method"
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