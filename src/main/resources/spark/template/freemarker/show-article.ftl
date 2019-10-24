<!DOCTYPE html>
<html lang="en">

<#include 'head.ftl'>

<body>

<#include 'navbar.ftl'>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <br/>
            <h2>${article.title}</h2>
            <br/>
            <p>${article.information}</p>
            <br/>
            <p>Published by: (AUTHOR) on ${article.date}</p>
            <br/>
            <a href="/articles/${article.uid}/edit" class="btn btn-dark">Edit</a>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<!-- /.container -->

<#include 'footer.ftl'>

</body>

</html>
