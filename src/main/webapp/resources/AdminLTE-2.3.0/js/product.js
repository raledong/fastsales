//初始化商品分类信息
$.ajax({
    url : "/fastsales/category/all",
    contentType : "JSON",
    type : "GET",
    success : function (data) {
        var productCategoryData = [];
        $.each(data, function(index,element){
            productCategoryData[index] = {'id' : element.categoryId,'text': element.name}
        });
        $("#category").select2({
            data : productCategoryData
        })
    }
})

$('#tags').select2({
    minimumInputLength: 1,
    maximumInputLength: 10,
    ajax : {
        url : "/fastsales/tag/search",
        dataType : "JSON",
        type : "POST",
        data : function(params){
            return {
                keyword : params.term
            }
        },
        processResults : function(data, params){
            console.log(data)

            return {
                results : $.map(data, function(item){
                    return {
                        text: item,
                        id: item
                    }
                })
            }
        },
    }
})

//点击添加明细按钮
$('#addSpec').bind("click", function(event){
    var html = '<div class="box box-widget spec">';
    html += '<div class="box-header with-border">'
    html +=    '<div class="form-group col-md-5">'
    html +=     '<input class="form-control" type="text" placeholder="请填写商品属性" name="properties"/>'
    html +=    '</div>'
    html +=    '<div class="form-group col-md-3">'
    html +=     '<input class="form-control" type="text" placeholder="商品数量" name="quantity"/>'
    html +=    '</div>'
    html +=    '<div class="form-group col-md-3">'
    html +=     '<input class="form-control" type="text" placeholder="每一包装中的商品数量" value="1" name="unit">'
    html +=    '</div>'
    html +=    '<div class="form-group col-md-1">'
    html +=     '<button class="btn btn-primary remove">删除</button>'
    html +=    '</div>'
    html +=   '</div>'
    html +=  '</div>'
    $("#specs").append(html)
    $('.remove').bind('click', function (event) {
        $(this).closest('div.box').remove()
    })

});

// //上传封面照片
// $(function () {
//     $('#cover').fileupload({
//         dataType: 'json',
//
//         done: function (e, data) {
//             if(data.result != null){
//                 console.log(data.result.fileURL)
//                 $('label[for="cover"] img').attr('src', data.result.fileURL)
//             }
//         },
//
//     });
// });

//提交新的商品信息
$('#createProduct').bind('click', function(event){
    $(this).prop('disabled', true)
    var token = $('input[name="token"]').val()
    var name = $('input[name="name"]').val()
    var shortId = $('input[name="shortId"]').val()
    var serializedId = $('input[name="serializedId"]').val()
    var categoryId = $('select[name="categoryId"]').val()
    var tags = $('select[name="tags"]').val()
    var comment = $('textarea[name="comment"]').val()
    var specs = [];
    $('.spec').each(function(index, element){
        var properties = $(this).find('input[name="properties"]').val()
        var quantity = $(this).find('input[name="quantity"]').val()
        var unit = $(this).find('input[name="unit"]').val()
        specs[index] = {
            'properties' : properties,
            'quantity' : quantity,
            'unit' : unit
        }
        console.log(specs[index])
    })
    var product = {
        'token' : token,
        'name' : name,
        'shortId' : shortId,
        'serializedId' : serializedId,
        'categoryId' : categoryId,
        'comment' : comment,
        'productSpecBeans' : specs==null ? [] : specs,
        'tags' : tags==null ? [] : tags,
    }
    console.log(product)


    // var formData = new FormData()
    // formData.append("token", token)
    // formData.append("name", name)
    // formData.append('productSpecBeans' , specs==null ? [] : specs)
    $.ajax({
        url : "/fastsales/product/add?token="+token,
        type : "POST",
        dataType : "JSON",
        contentType:"application/json",
        data : JSON.stringify(product),
        // contentType:false,
        // processData:false,
        // data : formData,
        success : function (data) {
            alert(data.FASTSALES_MESSAGE)
            if(data.FASTSALES_MESSAGE == '提交成功'){
                window.location.href='/fastsales/product/add';
            }
            $(this).prop('disabled', false)
        }
    })
})

$('.remove').bind('click', function (event) {
    $(this).closest('div.box').remove()
})

$('input[name="shortId"]').focusout(function (event) {
        var shortId = $(this).val();
        var node = $(this).closest('.form-group')
        node.removeClass('has-error')
        node.removeClass('has-success')
        if(shortId == ''){node.addClass('has-error'); return}
        $.ajax({
            url : "/fastsales/product/shid/"+shortId,
            type : "GET",
            dataType : "JSON",
            success : function (data) {
                console.log(data)
                if(data.FASTSALES_MESSAGE=='FASTSALES_SUCCESS'){

                    node.addClass('has-error')
                }else{
                    node.addClass('has-success')
                }
            }
        })
    }
)

// $('input[name="serializedId"]').focusout(function (event) {
//     var serializedId = $(this).val();
//     var node = $(this).closest('.form-group')
//     node.removeClass('has-error')
//     node.removeClass('has-success')
//     $.ajax({
//         url : "/fastsales/product/seid/"+serializedId,
//         type : "GET",
//         dataType : "JSON",
//         success : function (data) {
//             console.log(data)
//             if(data.FASTSALES_MESSAGE=='FASTSALES_SUCCESS'){
//                 node.addClass('has-error')
//             }else{
//                 node.addClass('has-success')
//             }
//         },
//         error : function (error) {
//             console.log(error)
//         }
//     })
// })

//商品上架界面
var productUnSelected = []
var productSelected = []
$('#category[refresh="true"]').change(function(){
    $('#briefProducts tr:gt(0)').remove()
    $.ajax({
        url : "/fastsales/product/search/category/"+$(this).val(),
        contentType : "JSON",
        type : "GET",
        success : function (data) {
            productUnSelected = []
            $.each(data, function(index,element){
                $.each(element.productSpecBeans, function (index2, element2) {
                    if(productSelected[element2.productSpecId]==null || productSelected[element2.productSpecId]==''){
                        productUnSelected[element2.productSpecId] = element2
                        var html = '<tr pid="'+ element2.productSpecId+'">'
                        html += '<td>' + element.shortId + '</td>'
                        html += '<td>' + element.name + '</td>'
                        html += '<td>' + element2.properties + '</td>'
                        html += '<td>1</td>'
                        html += '<td><button class="btn btn-primary addProduct">添加</button></td>'
                        html += '</tr>'
                        $('#briefProducts tbody').append(html)
                    }
                })
            });
            //点击添加后将当前行的内容添加到数据中
            addProductRow()

        }
    })
    console.log(productUnSelected)
})

removeRowButton()
function removeRowButton() {
    $('button.removeRow').bind('click', function (event) {
        productSelected[$(this).closest('tr').attr('pid')] = ''
        $(this).closest('tr').remove()
    })
}

addProductRow()
//点击添加后将当前行的内容添加到数据中
function addProductRow(){
    $('td button.addProduct').bind('click', function(event){
        var productSpecId = $(this).closest('tr').attr('pid')
        productSelected[productSpecId] = productUnSelected[productSpecId]
        var node = $(this).closest('tr').find('td:first-child')
        var html = ''
        html += '<tr pid=' + productSpecId + '>'
        html += '<td>' + productSpecId + '</td>'
        html += '<td>'+ node.text() + '</td>'
        node = node.next()
        html += '<td>' + node.text() + '</td>'
        node = node.next();
        html += '<td>' + node.text() + '</td>'
        html += '<td><input type="text" name="quantity" value="1" placeholder="输入商品数量" class="form-control"></td>'
        html += '<td><button class="btn btn-danger removeRow">删除</button></td>'
        html += '</tr>'
        $(this).closest('tr').remove();
        $('#selectedProducts tbody').append(html)
        removeRowButton()
    })
}

$('#showAllProducts').bind('click', function () {
    $('#briefProducts tr:gt(0)').remove()
    $.ajax({
        url : "/fastsales/product/all",
        contentType : "JSON",
        type : "GET",
        success : function (data) {
            productUnSelected = []
            $.each(data, function(index,element){
                $.each(element.productSpecBeans, function (index2, element2) {
                    if(productSelected[element2.productSpecId]==null || productSelected[element2.productSpecId]==''){
                        productUnSelected[element2.productSpecId] = element2
                        var html = '<tr pid="'+ element2.productSpecId+'">'
                        html += '<td>' + element.shortId + '</td>'
                        html += '<td>' + element.name + '</td>'
                        html += '<td>' + element2.properties + '</td>'
                        html += '<td>1</td>'
                        html += '<td><button class="btn btn-primary addProduct">添加</button></td>'
                        html += '</tr>'
                        $('#briefProducts tbody').append(html)
                    }
                })
            });
            //点击添加后将当前行的内容添加到数据中
            addProductRow()
        }
    })
})

$('#clearSelectedProduct').bind('click', function () {
    productSelected = []
    $('#selectedProducts tr:gt(0)').remove()
})
$('#searchProduct').bind('click', function () {
    var keyword  = $('#keyword').val();
    if(keyword!==''){
        $('#briefProducts tr:gt(0)').remove()
        $.ajax({
            url : "/fastsales/product/search/"+keyword,
            contentType : "JSON",
            type : "GET",
            success : function (data) {
                productUnSelected = []
                $.each(data, function(index,element){
                    $.each(element.productSpecBeans, function (index2, element2) {
                        if(productSelected[element2.productSpecId]==null || productSelected[element2.productSpecId]==''){
                            productUnSelected[element2.productSpecId] = element2
                            var html = '<tr pid="'+ element2.productSpecId+'">'
                            html += '<td>' + element.shortId + '</td>'
                            html += '<td>' + element.name + '</td>'
                            html += '<td>' + element2.properties + '</td>'
                            html += '<td>1</td>'
                            html += '<td><button class="btn btn-primary addProduct">添加</button></td>'
                            html += '</tr>'
                            $('#briefProducts tbody').append(html)
                        }
                    })
                });
                //点击添加后将当前行的内容添加到数据中
                addProductRow()
            }
        })
    }
})
$('#puton').bind('click', function (event) {
    $(this).prop('disabled', true)
    var name = $('input[name="name"]').val()
    var serializedId = $('input[name="serializedId"]').val()
    var price = $('input[name="defaultPrice"]').val()
    var productSpecs = []
    $('#selectedProducts tr:gt(0)').each(function (index, element) {
        var quantity = $(this).find('input[name="quantity"]').val()
        var id = $(this).attr('pid')
        productSpecs[index] = {
            'productSpecId' : id,
            'quantity' : quantity
        }
    })
    var salesItem = {
        'serializedId' : serializedId,
        'name' : name,
        'defaultPrice' : price,
        'salesItemSpecBeanList' : productSpecs
    }
    $.ajax({
        url : "/fastsales/salesitem/puton",
        type : "POST",
        dataType : "JSON",
        contentType:"application/json",
        data : JSON.stringify(salesItem),
        success : function (data) {
            alert(data.FASTSALES_MESSAGE)
            if(data.FASTSALES_MESSAGE == '提交成功'){
                window.location.href='/fastsales/salesitem/puton';
            }
            $(this).prop('disabled', false)
        }
    })
})