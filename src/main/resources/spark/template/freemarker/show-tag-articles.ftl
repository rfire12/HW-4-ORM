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

            <h1 class="my-4">Tag
                <small>${tag.tag}</small>
            </h1>

            <!-- Blog Post -->

            <#list articles as article>
                <div class="card mb-4">
                    <div class="card-body">
                        <h2 class="card-title">${article.title}</h2>
                        <p class="card-text">${article.information}</p>
                        <a href="articles/${article.uid}" class="btn btn-primary">Read More &rarr;</a>
                    </div>
                    <div class="card-footer text-muted">
                        <#list article.tags as tag>
                            <a href="#" class="badge badge-primary">${tag.tag}</a>
                        </#list>
                    </div>
                </div>
            </#list>


            <!-- Pagination -->
<#--            <ul class="pagination justify-content-center mb-4">-->
<#--                <ul class="pagination">-->
<#--                    <#list 1 ..<pages as page_number>-->
<#--                        <li class="page-item"><a class="page-link" href="/?page=${page_number}">${page_number}</a></li>-->
<#--                    </#list>-->
<#--                </ul>-->
<#--            </ul>-->

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!-- Search Widget -->
            <div class="card my-4">
                <h5 class="card-header">Search</h5>
                <div class="card-body">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                <button class="btn btn-secondary" type="button">Go!</button>
              </span>
                    </div>
                </div>
            </div>

            <!-- Categories Widget -->
            <div class="card my-4">
            </div>

            <!-- Side Widget -->
            <div class="card my-4">
                <h5 class="card-header">Side Widget</h5>
                <div class="card-body">
                    You can put anything you want inside of these side widgets. They are easy to use, and feature the
                    new Bootstrap 4 card containers!
                </div>
            </div>

        </div>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<#include 'footer.ftl'>

</body>

</html>
