<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>慕课书评网</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="../../resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="../../resources/raty/lib/jquery.raty.css">
    <script src="../../resources/jquery.3.3.1.min.js"></script>
    <script src="../../resources/bootstrap/bootstrap.min.js"></script>
    <script src="../../resources/art-template.js"></script>
    <script src="../../resources/raty/lib/jquery.raty.js"></script>
    <style>
        .highlight {
            color: red !important;
        }

        a:active {
            text-decoration: none !important;
        }
    </style>


    <style>
        .container {
            padding: 0;
            margin: 0;
        }

        .row {
            padding: 0;
            margin: 0;
        }

        .col- * {
            padding: 0;
        }
    </style>

    <script type="text/html" id="book">
        <a href="/book/{{bookId}}" style="color: inherit">
            <div class="row mt-2 book">
                <div class="col-4 mb-2 pr-2">
                    <img class="img-fluid" src="{{cover}}" alt="">
                </div>
                <div class="col-8  mb-2 pl-0">
                    <h5 class="text-truncate">{{bookName}}</h5>

                    <div class="mb-2 bg-light small  p-2 w-100 text-truncate">{{author}}</div>


                    <div class="mb-2 w-100">{{subTitle}}</div>

                    <p>
                        <span class="stars" data-score="{{evaluationScore}}" title="gorgeous"></span>
                        <span class="mt-2 ml-2">{{evaluationScore}}</span>
                        <span class="mt-2 ml-2">{{evaluationQuantity}}人已评</span>
                    </p>
                </div>
            </div>
        </a>
        <hr>
    </script>
    <script>

        $.fn.raty.defaults.path = "../../resources/raty/lib/images";

        function loadMore(isReset) {
            if (isReset) {
                $("#nextPage").val(1);
            }
            let nextPage = $("#nextPage").val();
            $.ajax({
                url: "/books",
                data: {
                    pageNumber: nextPage,
                },
                dataType: "json",
                success: function (json) {

                    let list = json["records"];
                    for (let i = 0; i < list.length; i++) {
                        let book = list[i];
                        let html = template("book", book);
                        $("#bookList").append(html);
                    }
                    $(".stars").raty({readonly: true});


                    if (json.current < json.pages) {
                        $("#nextPage").val(parseInt(json.current) + 1);
                        nextPage.val(parseInt(json.current) + 1);
                        $("#btnMore").show();
                        $("#divNoMore").hide();
                    } else {
                        $("#btnMore").hide();
                        $("#divNoMore").show();
                    }
                }
            })
        }

        $(function () {

            loadMore(true);

            /* 绑定 ”加载更多“ 按钮单击事件*/
            $("#btnMore").click(function () {
                loadMore(false);
            });

            $(".category").click(function () {
                $(".category").removeClass("highlight");
                $(".category").addClass("text-black-50");
                $(this).addClass("highlight");
            })
            $(".order").click(function () {
                $(".order").removeClass("highlight");
                $(".order").addClass("text-black-50");
                $(this).addClass("highlight");
            });

        })
    </script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-white shadow mr-auto">
        <ul class="nav">
            <li class="nav-item">
                <a href="/">
                    <img src="https://m.imooc.com/static/wap/static/common/img/logo2.png" class="mt-1"
                         style="width: 100px" alt="">
                </a>
            </li>

        </ul>
        <a href="" class="btn btn-light btn-sm">
            <img style="width: 2rem;margin-top: -5px" class="mr-1" src="../../images/user_icon.png" alt="">登录
        </a>
    </nav>


    <div class="row mt-2">


        <div class="col-8 mt-2">
            <h4>热评好书推荐</h4>
        </div>

        <div class="col-8 mt-2">
            <span data-category="-1" style="cursor: pointer" class="highlight  font-weight-bold category">全部</span>
            <span>|</span>

            <#-- 类别列表 -->
            <#list categoryList as category>
                <a style="cursor: pointer" data-category="${category.categoryId}"
                   class="text-black-50 font-weight-bold category">${category.categoryName}</a>
                <#if category_has_next>|</#if>
            </#list>

        </div>

        <div class="col-8 mt-2">
            <span data-order="quantity" style="cursor: pointer"
                  class="order highlight  font-weight-bold mr-3">按热度</span>

            <span data-order="score" style="cursor: pointer"
                  class="order text-black-50 mr-3 font-weight-bold">按评分</span>
        </div>
    </div>


    <div class="d-none">
        <input type="hidden" id="nextPage" value="2">
        <input type="hidden" id="categoryId" value="-1">
        <input type="hidden" id="order" value="quantity">
    </div>

    <div id="bookList">
    </div>

    <button type="button" id="btnMore" data-next-page="1" class="mb-5 btn btn-outline-primary btn-lg btn-block">
        点击加载更多...
    </button>
    <div id="divNoMore" class="text-center text-black-50 mb-5" style="display: none;">没有其他数据了</div>
</div>

</body>
</html>