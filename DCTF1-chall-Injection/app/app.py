import logging

from flask import Flask, render_template_string, request
from waitress import serve

#from lib.security import validate_login
from templates.error_page import error_page_template
from templates.index_page import index_page_template


app = Flask(__name__)
app.config['MESSAGE'] = 'You are getting closer!'

logger = logging.getLogger('waitress')
logger.setLevel(logging.INFO)

@app.errorhandler(404)
def page_not_found(e):
    evils = ['"/', "'/", "..", 'random', 'null', 'dev', 'zero', 'run', 'etc']
    path = request.path[1:]
    
    if any(evil in path for evil in evils):
        logger.info("{}: [EVIL] {}".format(request.remote_addr, path))
        path = ''
    else:
        logger.info("{}: {}".format(request.remote_addr, path))

    return render_template_string(error_page_template.format(path)), 404


@app.route('/')
def index():
    return render_template_string(index_page_template)


#@app.route('/login', methods=['POST'])
#def login():
#    if validate_login(request.form['name'], request.form['pass']):
#      #TODO: redirect to user page
#    else:
#      #TODO: wrong password
#


if __name__ == '__main__':
    serve(app, listen='*:8080')
