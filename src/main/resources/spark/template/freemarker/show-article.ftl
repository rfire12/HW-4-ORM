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
            <div class="card">
                <div class="card-body">
                    <br/>
                    <h2>${article.title}</h2>
                    <br/>
                    <p>${article.information}</p>
                    <br/>
                    <p>Published by: (AUTHOR) on ${article.date}</p>
                    <br/>
                    <a href="/articles/${article.uid}/edit" class="btn btn-dark">Edit</a>
                    <br/>
                    <h3>Tags:</h3>
                    <ul>
                        <#list tags as tag>
                            <li>
                                ${tag.tag}
                            </li>
                        </#list>
                    </ul>
                    <form action="/comments/new/${article.uid}">
                        <textarea placeholder="Comment here" class="form-control"></textarea>
                        <button type="submit" class="btn btn-primary">Post</button>
                    </form>
                    <h3>Comments:</h3>
                    <ul>
                        <#list comments as comment>
                            <li>
                                ${comment.comment}
                            </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<!-- /.container -->

<#include 'footer.ftl'>

</body>

</html>
