from datetime import datetime, timedelta 
from functools import wraps 

from flask import Flask, request, jsonify 
import jwt
from waitress import serve


app = Flask(__name__)

JWT_SECRET = '147852369'
CTF_TOKEN = "dctf{w34k_k3y5_4r3_n0t_0k4y}"

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = None
        if 'Authorization' in request.headers and request.headers['Authorization'].startswith('Bearer '):
            token = request.headers['Authorization'][len('Bearer '):]

        if not token:
            return jsonify({
                'Error' : 'Authorization header not found! Try to login with guest credentials.'
            }), 401
   
        try: 
            data = jwt.decode(token, JWT_SECRET, algorithms=['HS512']) 
            username = data['username']
        except Exception as e: 
            return jsonify({ 
                'message' : 'Authorization failed!'
            }), 401

        return  f(username, *args, **kwargs)
   
    return decorated
   
@app.route('/') 
@token_required
def index(current_user):
    if current_user == 'admin':
        return jsonify({
            'Message': "Hi, admin! I have a secret for you.",
            'Secret': CTF_TOKEN
        })
    
    return jsonify({
        'Message': "Hi, {}! You are not admin, I have no secret for you.".format(current_user)
    })
   
@app.route('/login', methods =['POST'])
def login(): 
    auth = request.form
   
    if not auth or not auth.get('username') or not auth.get('password'): 
        return jsonify({
            'Error': 'Missing username or password field.'
        }), 401
    
    if auth['username'] == 'guest' and auth['password'] == 'guest':
        token = jwt.encode({
            'username': 'guest',
            'exp' : datetime.utcnow() + timedelta(minutes = 30)
        }, JWT_SECRET, algorithm='HS512')

        return jsonify({
            'Token' : "Bearer " + token
        })
    else: 
        return jsonify({
            'Error' : 'Invalid credentials!'
        }), 401


if __name__ == "__main__": 
    serve(app, listen='*:8080')
