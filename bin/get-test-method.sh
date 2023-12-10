#!/bin/zsh

# Check if at least two arguments are provided
if [ "$#" -lt 2 ]; then
  echo "Usage: $0 <filename.json> <key>"
  exit 1
fi

# Read the filename and key from the command line arguments
filename="$1"
specified_key="$2"

# Add debugging statements
echo "Filename: $filename"
echo "Specified Key: $specified_key"

# Check if the file exists
if [ ! -f "$filename" ]; then
  echo "Error: File '$filename' not found."
  exit 1
fi

# Check if jq is installed
if ! command -v jq &> /dev/null; then
  echo "Error: jq is not installed. Please install jq before running this script."
  exit 1
fi

# Extract values associated with the specified key
values=$(jq -r ".[\"$specified_key\"] | .[]" "$filename")

# Check if the specified key exists
if [ "$values" = "null" ]; then
  echo "Error: Key '$specified_key' not found in the JSON file."
  exit 1
fi

# Print the values associated with the specified key
echo "$values"
