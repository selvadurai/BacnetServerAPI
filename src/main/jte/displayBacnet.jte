@args ArrayList<String> arrayList

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JTE Template</title>
</head>
<body>
    <h1>ArrayList Elements:</h1>
    <ul>
        @for ( var element : arrayList)
            <li>${element}</li>
        @endfor
            
    </ul>
</body>
</html>