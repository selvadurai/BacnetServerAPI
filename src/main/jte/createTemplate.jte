<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JTE Template with Form</title>
</head>
<body>

    <h1>JTE Template with Form</h1>

    <!-- Form Section -->
    <form action="/findBacnetObjects" method="post">
        <!-- Text Area for JSON Payload -->
        <label for="jsonPayload">Enter JSON Payload:</label>
        <br><br>
        <textarea id="jsonPayload" name="jsonPayload" rows="10" cols="40" ></textarea>
        <br>
        <!-- Submit Button -->
        <button type="submit">Submit</button>
    </form>

</body>
</html>