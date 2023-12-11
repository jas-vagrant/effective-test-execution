#!/bin/bash


file=$file
#file="src/main/java/com/testvagrant/example/implementation/BasicCalculator.java"
file_name="bin/tests-map.json"
keys=($(jq -r 'keys_unsorted[]' "$file_name"))

class_path="${file//\//.}"
class_path="${class_path#src.main.java.}"
class_path=${class_path%.java}
echo "class_path: $class_path"

declare -a method_name_list=()
declare -a method_range_list=()

for key in "${keys[@]}"; do
    if [[ $key == $class_path* ]]; then
        # Extract method name
        method_name="${key##*.}"
        echo "method_name: $method_name"

        start_pattern=".*$method_name.*(.*) {"
        end_pattern="^[[:space:]]*}$"

        start_line_number=$(grep -n "$start_pattern" "$file" | cut -d ":" -f 1)

        # Use awk to find the end line considering nested braces
        end_line_number=$(awk -v start_line="$start_line_number" -v end_pattern="$end_pattern" '
            NR >= start_line {
                count += gsub(/{/, "&")
                count -= gsub(/}/, "&")
                if (count == 0) {
                   print NR
                   exit
                }
            }
        ' "$file")

        echo "Range : $start_line_number-$end_line_number"

        method_name_list+=("$method_name")
        method_range_list+=("$start_line_number $end_line_number")
    fi
done

export method_name_list
export method_range_list