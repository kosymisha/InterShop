<#import "../parts/common.ftl" as c>
<@c.page "InterShop"  "">
<script>

    function asd() {
        var inputs = document.getElementById("price");
        for (var i = 0; i < inputs.length; i++) {
            var input = inputs[i];
            var state = {
                value: input.value,
                start: input.selectionStart,
                end: input.selectionEnd,
                pattern: RegExp('^' + '\\d{1, 6}(\\.?\\d{2,1})' + '$')
            };
            input.addEventListener('input', function (event) {
                if (state.pattern.test(input.value)) {
                    state.value = input.value;
                } else {
                    input.value = state.value;
                    input.setSelectionRange(state.start, state.end);
                }
            });
            input.addEventListener('keydown', function (event) {
                state.start = input.selectionStart;
                state.end = input.selectionEnd;
            });
        }
    }
</script>
    <link href="/path/to/picker.css"
    <form action="/announcements" method="post" enctype="multipart/form-data">
        <select name="category">
            <option value="Coats & Jackets">Coats & Jackets</option>
            <option value="Pants">Pants</option>
            <option value="Shirts">Shirts</option>
            <option value="Athletic Shoes">Athletic Shoes</option>
        </select>
        <div><input type="text" name="title" placeholder="input title" /></div>
        <div><input type="file" name="photo_url" value="Add file" /></div>
        <div><input type="text" name="description" placeholder="input description" /></div>
        <div><input type="text" name="price" id="price" placeholder="input price" onchange="asd()" /></div>
        <select name="shop">
            <#list shops as shop>
                <option value="${shop.nameShop}">${shop.nameShop}</option>
            </#list>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Create" /></div>
    </form>
</@c.page>