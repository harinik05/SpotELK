import json
import argparse
import sys

def load_data(file_path):
    """Load JSON data from the given file path."""
    try:
        with open(file_path, 'r') as file:
            return json.load(file)
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
        sys.exit(1)
    except json.JSONDecodeError:
        print("Error: Invalid JSON format.")
        sys.exit(1)

def query_category(data, category, filters):
    """Query a specific category from the data and apply filters."""
    categories = data.get("categories", {})
    if category not in categories:
        print(f"Category '{category}' not found.")
        sys.exit(1)
    
    # Apply filters to the results
    results = categories[category]
    for key, value in filters.items():
        results = [item for item in results if item.get(key) and item[key].lower() == value.lower()]
    
    return results

def main():
    parser = argparse.ArgumentParser(description="Query a JSON dataset with optional filters.")
    parser.add_argument("file", help="Path to the JSON data file.")
    parser.add_argument("category", help="Category to query (e.g., books, movies, music).")
    parser.add_argument("--author", help="Filter by author (for books).", default=None)
    parser.add_argument("--director", help="Filter by director (for movies).", default=None)
    parser.add_argument("--artist", help="Filter by artist (for music).", default=None)
    parser.add_argument("--year", help="Filter by year.", default=None)
    args = parser.parse_args()

    # Load data from the specified JSON file
    data = load_data(args.file)

    # Prepare filters from optional arguments
    filters = {k: v for k, v in vars(args).items() if k in {"author", "director", "artist", "year"} and v}

    # Query the specified category with filters
    results = query_category(data, args.category, filters)

    # Display results
    if results:
        print(json.dumps(results, indent=4))
    else:
        print("No matching results found.")

if __name__ == "__main__":
    main()
