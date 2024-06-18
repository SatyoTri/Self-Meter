# Use the official Python image from the Docker Hub
FROM python:3.9-slim

# Set environment variables
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

# Set the working directory in the container
WORKDIR /app

# Copy the requirements.txt file into the container at /app
COPY requirement.txt /app/

# Install the Python dependencies
RUN pip install --upgrade pip
RUN pip install -r requirement.txt

# Copy the rest of the application code into the container at /app
COPY . /app/

# Set environment variables for Flask
ENV FLASK_APP=app
ENV FLASK_RUN_HOST=0.0.0.0

# Expose the port the app runs on
EXPOSE 8080

# Run the Flask application
CMD ["python", "server.py", "--host=0.0.0.0", "--port=8080"]
