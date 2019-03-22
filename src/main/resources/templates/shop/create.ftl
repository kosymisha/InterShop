<#import "../parts/common.ftl" as c>

<@c.page "Create shop">

    <form action="/shops/create" method="post" enctype="multipart/form-data">
        <div><input type="text" name="name_shop" placeholder="input name" /></div>
        <div><input type="text" name="url" placeholder="input shop url" /></div>
        <div><input type="text" name="description" placeholder="input description" /></div>
        <div><input type="file" name="photo_url" value="Add file" /></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Create" /></div>
    </form>

</@c.page>