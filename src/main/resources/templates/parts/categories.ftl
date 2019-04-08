<script src="/js/searchMain.js" type="text/javascript" xmlns="http://www.w3.org/1999/html"></script>
<style>
    .catdiv{
        overflow-x: auto;
        overflow-y: hidden;
    }
</style>
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
                    <input type="radio" name="options" value="${category.getIdString()}" autocomplete="off" >${category.categoryName}
                </label>
            </object>
        </#list>
    </div>
</div>
