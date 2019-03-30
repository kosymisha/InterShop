<div class="row mt-3 ml-2 mr-2" id="categoryList">
    <div class="catdiv">
        <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-secondary active">

                <input type="radio" name="options" value="0" id="option1" autocomplete="off" checked> None

            </label>
        <#list categories as category>
            <label class="btn btn-secondary">

                <input type="radio" name="options" value="${category.id}" autocomplete="off"> ${category.categoryName}

            </label>
        </#list>
        </div>
    </div>