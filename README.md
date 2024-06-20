# Project Documentation

## Table of Contents
- [Overview](#overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Prediction Endpoint](#prediction-endpoint)
  - [Questions Endpoint](#questions-endpoint)
- [License](#license)

## Overview
This project is a Flask-based web application that provides prediction services via a RESTful API. It includes two main endpoints: one for making predictions and another for managing questions.

## Getting Started

### Prerequisites
- Python 3.x
- Flask
- Flask-CORS
- NumPy
- A machine learning model and labels (as `model` and `labels` in `app/models/model.py`)
- A Firestore client setup (as `FirestoreClient` in `app/storeData/firestore.py`)

### Installation
1. Clone the repository:
   ```Terminal
   git clone -b Cloud-Computing https://github.com/SatyoTri/Self-Meter.git
   cd Cloud-Computing
   ```
  ```Terminal
  pip install -r requirements.txt
  ```

### Running The Application
```Terminal
python app.py
```
### Api Endpoints
Prediction Endpoint
URL: /predict
Method: POST

Request Body : 
```json
{
    "input_data": [26,1,0,0,0,1,1,2,0,0,1,0,0,1,1,1,0,0,2,0,0,0,1,0,1,1,0,1]
}
```
Response : 
"200 OK"
```json
{
    "createdAt": "2024-06-20 07:25:19",
    "id": 5108,
    "predicted_label": "OCD",
    "predicted_percentages": {
        "ADHD": 7.793405532836914,
        "ASD": 1.5310561656951904,
        "Anxiety": 4.3592400550842285,
        "Bipolar": 2.7529900074005127,
        "Eating Disorder": 0.8912571668624878,
        "Loneliness": 4.544558525085449,
        "MDD": 3.8957362174987793,
        "OCD": 32.05075454711914,
        "PDD": 0.9524543881416321,
        "PTSD": 6.526025295257568,
        "Psychotic Depression": 4.4362006187438965,
        "Sleeping Disorder": 30.266321182250977
    }
}
```
URL: /questions
Method: GET
Response : 
"200 OK"
```json
  "questions": [
        "How old are you ? ",
        "Do you often feel nervous ? ",
        "Do you experience panic attacks ?",
        "Do you find yourself breathing rapidly when stressed ?",
        "Do you sweat excessively without physical exertion ?",
        "Do you have trouble concentrating on tasks ?",
        "Do you have difficulty sleeping ?",
        "Do you find it hard to keep up with work or school ?",
        "Do you often feel hopeless ?",
        "Do you get angry easily?",
        "Do you tend to overreact in stressful situations?",
        "Have you noticed any changes in your eating habits?",
        "Do you have thoughts of suicide ?",
"..................................."
]
```


