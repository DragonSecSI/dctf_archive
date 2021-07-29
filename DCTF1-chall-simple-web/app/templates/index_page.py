index_page_template = '''
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    }
    fieldset{
    border: 1px solid;
    width: 400px;
    margin:auto;
    }
    </style>
</head>
<body>
    <form action='flag' method='post'>
        <fieldset>
            <input type="checkbox" name="flag" value="1">
            I want flag!<br>
            <input hidden name="auth" value="0">
            <input type='submit' name='Submit' value='Submit' />
        </fieldset>
    </form>
</body>
</html>
'''