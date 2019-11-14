<!DOCTYPE html>
<html lang="en">

<#include 'head.ftl'>

<body>

<#include 'navbar.ftl'>

<br/>
<!-- Page Content -->
<div class="container">
    <div class="row">

        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <br/>
                    <h2>${article.title}</h2>
                    <br/>
                    <p>${article.information}</p>
                    <br/>
                    <p>Published by: ${article.author.username} on ${article.date}</p>

                    <br/><br/>
                    <#--<h3>Tags:</h3>
                    <#list tags as tag>
                        <a href="#" class="badge badge-primary">${tag.tag}</a>
                    </#list>-->
                    <br/><br/><br/>
                    <#if user??>
                        <form action="/comments/new/${article.uid}" method="post">
                            <textarea placeholder="Comment here" class="form-control" name="comment"></textarea>
                            <br/>
                            <button type="submit" class="btn btn-primary">Post</button>
                        </form>
                    </#if>
                    <br/>
                    <h3>Comments:</h3>
                    <hr/>
                    <#list comments as comment>
                        <p>
                            ${comment.author.username} commented:
                        </p>
                        <p>
                            ${comment.comment}
                        </p>
                        <#if user?? && (user.role == "admin" || user.uid == comment.author.uid)>
                            <form action="/articles/${article.uid}/comments/${comment.uid}" method="post">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </#if>
                        <hr/>
                    </#list>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <#if user?? && (user.role == "admin" || user.uid = article.author.uid)>

                    <!-- Search Widget -->
                    <div class="card my-4">
                        <h5 class="card-header">Settings</h5>
                        <div class="card-body">
                            <a href="/articles/${article.uid}/edit" class="btn btn-dark">Edit this article</a>
                            <form action="/articles/${article.uid}/delete" method="post">
                                <button type="submit" class="btn btn-danger">Delete this article</button>
                            </form>
                        </div>
                    </div>
            </#if>

            <#if user??>
                <div class="card my-4">
                    <h5 class="card-header">Valoraciones</h5>
                    <div class="card-body">
                        <form action="/articles/${article.uid}/like" class="formRecommendations" method="post">
                            <#if like == "null" || like == "false" >
                                <button class="like color-grey" name="like" type="submit">
                                    <span class="fa fa-thumbs-o-up color-grey" aria-hidden="true">&#128077;</span>
                                </button>
                            <#else>
                                <button class="like" name="like" type="submit">
                                    <span class="fa fa-thumbs-o-up" aria-hidden="true">&#128077;</span>
                                </button>
                            </#if>
                        </form>
                        <form action="/articles/${article.uid}/dislike" class="formRecommendations" method="post">
                            <#if like == "null" || like == "true" >
                                <button class="dislike color-grey" name="dislike" type="submit">
                                    <span class="fa fa-thumbs-o-down color-grey" aria-hidden="true">&#128078;</span>
                                </button>
                            <#else>
                                <button class="dislike" name="dislike" type="submit">
                                    <span class="fa fa-thumbs-o-down" aria-hidden="true">&#128078;</span>
                                </button>
                            </#if>
                        </form>
                        <span class="likes-total">A <b>${likesTotal}</b> personas les gusta este post</span>
                        <span class="likes-total">A <b>${dislikesTotal}</b> personas no les gusta este post</span>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>
<!-- /.container -->

<#include 'footer.ftl'>

</body>

</html>
