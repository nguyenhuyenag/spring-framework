<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <title>MongoDB</title>
    <link rel="shortcut icon" href="#">
    <link rel="icon" href="//spring.io/images/favicon-9d25009f65637a49ac8d91eb1cf7b75e.ico" type="image/x-icon">
    <!-- css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            counter-reset: section;
        }
        h3::before {
            counter-increment: section;
            content: ""counter(section) ") ";
        }
        a {
            text-decoration: none;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>MongoDB</h1>
        <h3>Spring JPA</h3>
        <ol>
            <li><a href="jpa/insert" target="_blank">Insert (Post)</a></li>
            <li><a href="jpa/update" target="_blank">Update (Post)</a></li>
            <li><a href="jpa/find-by-word?word=beef" target="_blank">Find by word</a></li>
            <li><a href="jpa/delete-by-word?word=beef" target="_blank">Delete by word</a></li>
            <li><a href="jpa/find-all" target="_blank">Find all</a></li>
            <li><a href="jpa/find-between?from=10&to=15" target="_blank">Find between</a></li>
            <li><a href="jpa/find-all-and-sort" target="_blank">Find all and sort</a></li>
            <!-- JSON -->
            <li><a href="jpa/find-by-json?word=among" target="_blank">Find by JSON</a></li>
            <li><a href="jpa/find-between-by-json?from=11&to=12" target="_blank">Find between by JSON</a></li>
            <li><a href="jpa/find-with-or-conditons" target="_blank">Find with OR conditions ???</a></li>
            <li><a href="jpa/find-with-and-conditons" target="_blank">Find with AND conditions ???</a></li>
            <li><a href="jpa/find-and-sort-by-json" target="_blank">Find and sort by JSON</a></li>
            <!-- Regex -->
            <li>
                <form id="regexForm">
                    Find with regex
                    <input id="regex" value="^a" placeholder="Regex">
                    <input type="submit" value="Find" onClick='submitForm()'>
                </form>
            </li>
        </ol>
        <h3>MongoTemplate</h3>
        <ol>
            <li><a href="template/find-all" target="_blank">Find all</a></li>
            <li><a href="jpa/find-all-sort-by-word" target="_blank">Find all and sort by word</a></li>
        </ol>
    </div>
    <script>
        function submitForm() {
            let url = encodeURI('jpa/find-with-regex?regex=' + $('#regex').val());
            var win = window.open(url, '_blank');
        }
    </script>
</body>

</html>
