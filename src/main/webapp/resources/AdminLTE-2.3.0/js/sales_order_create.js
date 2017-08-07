var salesSource = [
    {id : 0, text : '实体店'},
    {id : 1, text : '微店'},
    {id : 2, text : '淘宝'}
]
$('#salesSource').select2({
    data : salesSource
})
$('select#customer').select2({
    minimumInputLength: 1,
    maximumInputLength: 10,
    ajax : {
        url : "/fastsales/customer/brief",
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
                        text: item.name,
                        id: item.customerId
                    }
                })
            }
        }
    }
})

// 清空列表按钮
$('div>button.remove').bind('click', function(event){
    salesItemSelected = []
    $('#orderItems tr:gt(0)').remove();
    resetSum(0,0)
})

//删除单项商品按钮
bindRemoveButton()
function bindRemoveButton(){
    $('td>button.remove').bind('click', function(event){
        salesItemSelected[$(this).closest('tr').attr('sid')] = ''
        $(this).closest('tr').remove()
        resetSum(calculateSum(),0)
    })
}


//单价或者是数量被修改时
bindInputChange()
function bindInputChange(){
    $('td input').change(function(){
        var node =  $(this).closest('tr')
        var singlePrice = node.find('td.singlePrice input').val()
        var singleQuantity = node.find('td.singleQuantity input').val()
        node.find('td.singleSum').text(singlePrice*singleQuantity)
        resetSum(calculateSum(), 0)
    })
}

function calculateSum(){
    var sum = 0;
    $('#orderItems tr:gt(0)').each(function(element, index){
        sum += parseFloat($(this).find('td.singleSum').text());
    })
    return sum
}

function resetSum(sum, hasPaid){
    $('#sum').text(sum);
    $('#hasPaid').val(hasPaid);
    $('#remain').text(sum - hasPaid);
}

$('input[name="hasPaid"]').bind('change',function(event){
    var sum = parseFloat($('#sum').text())
    var hasPaid = parseFloat($(this).val())
    if(hasPaid > sum){
        alert("实收金额比应收金额高！")
    }else{
        resetSum(sum, hasPaid)
    }
})
//未选中的商品
var salesItemUnselected = []
//选中的商品
var salesItemSelected = []
//将选中的商品添加到商品列表中
addSalesItemRow()
function addSalesItemRow(){
    $('td button.addToSalesOrder').bind('click',function(event){
        var sid = $(this).closest('tr').attr('sid')
        $(this).closest('tr').remove()
        salesItemSelected[sid] = salesItemUnselected[sid]
        salesItemUnselected[sid]=''
        var html = ''
        html += '<tr sid="'+ sid +'">'
        html += '<td>'+ sid +'</td>'
        //@Todo 商品信息详情，待开发
        html += '<td data-toggle="tooltip" data-placement="top" title="'+salesItemSelected[sid].salesItemSpecsInfo+'">'+salesItemSelected[sid].name+'</td>'
        html += '<td class="singlePrice"><input type="text" name="" value="' + salesItemSelected[sid].price + '" placeholder="输入商品数量" class="form-control"></td>'
        html += ' <td class="singleQuantity"><input type="text" name="" value="1" placeholder="输入商品数量" class="form-control"></td>'
        html += '<td class="singleSum">' +salesItemSelected[sid].price + '</td>'
        html += '<td><button class="btn btn-danger remove">删除</button><button class="btn btn-primary">最近售价</button></td>'
        html += '</tr>'
        $('#orderItems tbody').append(html)
        bindRemoveButton()
        bindTooltip()
        bindInputChange()
        resetSum(calculateSum(), 0)
    })

}

bindTooltip()
function bindTooltip(){
    $('[data-toggle="tooltip"]').tooltip({
        container: 'body'
    })
}

$('#showAllBriefSalesItems').bind("click", function (event) {
    $('#briefSalesItems tr:gt(0)').remove()
    salesItemUnselected = []
    $.ajax({
        url : "/fastsales/salesitem/b/all",
        dataType : "JSON",
        type : "GET",
        success : function (data) {
            var html = "";
            $.each(data, function (index, element) {
                if(salesItemSelected[element.salesItemId]==null || salesItemSelected[element.salesItemId]==''){
                    salesItemUnselected[element.salesItemId] = element
                    html += '<tr sid="'+element.salesItemId+'">'
                    html +=     '<td>' + element.salesItemId+ '</td>'
                    html +=     '<td>' + element.name + '</td>'
                    html +=     '<td>'+ element.available+ '</td>'
                    html +=     '<td><button class="btn btn-primary addToSalesOrder">添加</button></td>'
                    html += '</tr>'
                }

            })
            $('#briefSalesItems tbody').append(html)
            addSalesItemRow()
            console.log(data)
        },
        error : function (data) {
            console.log(data)
        }
    })
})

$('#searchByKeyword').bind('click', function (event) {
    var keyword = $('#keyword').val()
    if(keyword=='') alert("请输入关键字")
    else{
        $('#briefSalesItems tr:gt(0)').remove()
        salesItemUnselected = []
        $.ajax({
            url : "/fastsales/salesitem/b/keyword/"+keyword,
            dataType : "JSON",
            type : "GET",
            success : function (data) {
                var html = "";
                $.each(data, function (index, element) {
                    if(salesItemSelected[element.salesItemId]==null || salesItemSelected[element.salesItemId]==''){
                        salesItemUnselected[element.salesItemId] = element
                        html += '<tr sid="'+element.salesItemId+'">'
                        html +=     '<td>' + element.salesItemId+ '</td>'
                        html +=     '<td>' + element.name + '</td>'
                        html +=     '<td>'+ element.available+ '</td>'
                        html +=     '<td><button class="btn btn-primary addToSalesOrder">添加</button></td>'
                        html += '</tr>'
                    }
                })
                $('#briefSalesItems tbody').append(html)
                addSalesItemRow()
                console.log(data)
            },
            error : function (data) {
                console.log(data)
            }
        })
    }
})