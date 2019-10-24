<!DOCTYPE html>
<html lang="en">

<#include 'head.ftl'>

<body>

<#include 'navbar.ftl'>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">Edit
                <small>Article</small>
            </h1>

            <form action="/new-article" method="POST">
                <div class="form-group">
                    <label for="exampleInputEmail1">Title</label>
                    <input type="text" class="form-control" id="title" name="title"
                           placeholder="Enter your article title" value="${article.title}">
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Article</label>
                    <textarea class="form-control" id="article-body" name="article-body"
                              rows="7">${article.information}</textarea>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">Tags</label>
                    <input type="text" class="form-control" id="tags" name="tags"
                           placeholder="e.g: Javascript, Python, Ruby" value="${tags}">
                </div>
                <button type="submit" class="btn btn-primary">Update Article</button>
            </form>


        </div>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<#include 'footer.ftl'>

</body>

</html>
