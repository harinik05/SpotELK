import json
import argparse
import openai
import os

# Set your OpenAI API key (use environment variables for security)
openai.api_key = os.getenv("OPENAI_API_KEY")

def load_data(file_path):
    """Load JSON data from the given file path."""
    try:
        with open(file_path, 'r') as file:
            return json.load(file)
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
        exit(1)
    except json.JSONDecodeError:
        print("Error: Invalid JSON format.")
        exit(1)

def generate_kql_query(prompt, data_structure):
    """Generate a KQL query using OpenAI."""
    system_prompt = (
        "You are an expert in Kibana Query Language (KQL). "
        "Given the following JSON log data structure, generate a KQL query based on the user's prompt. "
        "The query should use field names as they appear in the data structure. "
        "If the prompt mentions filters, include them in the KQL query. Return only the query string."
    )
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=[
                {"role": "system", "content": system_prompt},
                {"role": "user", "content": f"JSON Data Structure:\n{json.dumps(data_structure, indent=4)}"},
                {"role": "user", "content": f"Prompt: {prompt}"}
            ],
            max_tokens=100,
            temperature=0.7
        )
        return response['choices'][0]['message']['content'].strip()
    except Exception as e:
        print(f"Error generating query: {e}")
        exit(1)

def main():
    parser = argparse.ArgumentParser(
        description="Generate Kibana Query Language (KQL) queries using AI based on JSON log data structure and a user prompt."
    )
    parser.add_argument("file", help="Path to the JSON file containing the log data structure.")
    parser.add_argument("prompt", help="The prompt describing the query you want to generate.")
    args = parser.parse_args()

    # Load JSON log data structure
    data_structure = load_data(args.file)

    # Generate KQL query
    query = generate_kql_query(args.prompt, data_structure)

    # Print the generated query
    print("\nGenerated KQL Query:")
    print(query)

if __name__ == "__main__":
    main()
