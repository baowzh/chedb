var selectedItems = new Array();
var lowerprice = -9999;
var upperprice = -9999;
var showsearchitems = function(type) {
	if (type == 1) {
		$("#searchindex").hide();
		$("#searcheitems").show();
	} else if (type == 2) {
		$("#searchindex").show();
		$("#searcheitems").hide();
		setSelectedItems();
	} else if (type == 3) {
		$("#searchindex").show();
		$("#searcheitems").hide();
	}

};

var switchstatus = function(divid) {
	if (checkexist(divid)) {
		var index = getItemIndex(divid);
		this.selectedItems.splice(index, 1);
		$("#" + divid).removeClass('textIconActive');
		$("#" + divid).addClass('textIcon');
	} else {
		$("#" + divid).removeClass('textIcon');
		$("#" + divid).addClass('textIconActive');
		var seleItem = new selectedItem();
		seleItem.setId(divid);
		for (i in serviceItems.items) {
			var items = serviceItems.items[i].childitems;
			for (j in items) {
				if (items[j].labelId == divid) {
					seleItem.setTitle(items[j].title);
					break;
				}
			}
		}
		this.selectedItems.push(seleItem);
	}
}

var checkexist = function(id) {
	for (i in selectedItems) {
		if (selectedItems[i].id == id) {
			return true
		}
	}
	return false;
};

var getItemIndex = function(id) {
	for (i in selectedItems) {
		if (selectedItems[i].id == id) {
			return i;
		}
	}
	return -1;
};

var selectedItem = function() {
	this.id = null;
	this.title = null;
	this.setId = function(id) {
		this.id = id;
	};
	this.setTitle = function(title) {
		this.title = title;
	}
};
/**
 * 设置被选择的元素
 */
var setSelectedItems = function() {
	var selstr = '';
	var pricestr = '价格不限';
	for (i in selectedItems) {
		selstr = selstr + selectedItems[i].title + ",";
	}
	lowerprice = $('#lowerprice').val();
	upperprice = $('#upperprice').val();
	if (upperprice != '' && lowerprice == '') {
		pricestr = "价格低于" + $('#upperprice').val() + '元';
	} else if (upperprice != '' && lowerprice != '') {
		pricestr = "价格介于" + $('#lowerprice').val() + '-'
				+ $('#upperprice').val() + '元';
	} else if (upperprice == '' && lowerprice != '') {
		pricestr = "价格高于" + $('#lowerprice').val() + '元';
	}
	if (selstr != '') {
		$("#selectedItems").html(pricestr + ', ' + selstr);
	} else {
		$("#selectedItems").html(pricestr + '，项目不限');
	}

}
