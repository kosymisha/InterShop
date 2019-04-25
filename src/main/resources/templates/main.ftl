

<#import "parts/common.ftl" as c>
<@c.page "InterShop">
<script src="/js/searchMain.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<style>
    .catdiv{
        overflow-x: auto;
        overflow-y: hidden;
    }
</style>
<div class="row">
    <div class="col">
        <div class="row mt-3 ml-2 mr-2" id="categoryList">
            <input type="text" class="form-control mb-1" placeholder="Search category..." id="keywordCategory" aria-describedby="basic-addon2" onkeyup="inputCategory()">
            <div class="catdiv" id="toch">
            <div class="btn-group btn-group-toggle" data-toggle="buttons">
                <object class="btn btn-secondary">
                    <label class="btn btn-secondary active">
                        <input type="radio" name="options" value="0" id="option1" autocomplete="off" checked>None
                    </label>
                </object>
                    <#list categories as category>
                    <object name="objects" class="btn btn-secondary" >
                        <label class="btn btn-secondary">
                            <input type="radio" name="options" value="${category.id}" autocomplete="off" >${category.categoryName}
                        </label>
                    </object>
                    </#list>
            </div>
            </div>
        </div>
        <div class="row mt-3 ml-3 mr-3">
            <div class="col">
                <div class="row">
                    <div class="col">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search items..." id="keyword" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button id="searchbtn" class="btn btn-outline-secondary" onclick="search()">Search</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="btn btn-outline-secondary">Min $</span>
                            </div>
                            <input type="text" class="form-control" id="minPrice" aria-label="Amount (to the nearest dollar)" onkeyup="isValidPrice('minPrice')" placeholder="0.00">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="btn btn-outline-secondary">Max $</span>
                            </div>
                            <input type="text" class="form-control" id="maxPrice" aria-label="Amount (to the nearest dollar)" onkeyup="isValidPrice('maxPrice')" placeholder="0.00">
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="btn btn-outline-secondary" for="inputGroupSelect01">Sort</label>
                            </div>
                            <select class="custom-select" id="inputGroupSelect01">
                                <option value="0" selected>None</option>
                                <option value="1">Most viewed</option>
                                <option value="2">Price low to high</option>
                                <option value="3">Price high to low</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
<div class="col">
    <div align="center" id="spinnerDiv">${session}</div>
    <div id="advs"></div>
</div>
</div>
</@c.page>