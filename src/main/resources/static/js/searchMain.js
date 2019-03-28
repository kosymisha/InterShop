function search() {
    var sort = document.getElementById("inputGroupSelect01").value;
    var category = document.querySelector('input[name="options"]:checked').value;
    var keyword = document.getElementById("keyword").value;
    var maxPrice = document.getElementById("maxPrice").value;
    var minPrice = document.getElementById("minPrice").value;
    alert('sort: ' + sort +
    '\ncategory: ' + category +
    '\nkeyword: ' + keyword +
    '\nmax price: ' + maxPrice +
    '\nmin price: ' + minPrice);
}

function isValidPrice(point) {
    //^\d{0,8}(\.\d{1,2})?$
    if(!/^\d{0,8}(\.\d{1,2})?$/.test(document.getElementById(point).value)){
        document.getElementById(point).style.color = "red";
    } else { document.getElementById(point).style.color = "#495057"; }
}
