//namespace support
var Frontier = Frontier || {};
Frontier.AddressAdd = Frontier.AddressAdd || {};
//end namespace support

Frontier.AddressAdd = new function() {

	var uiConfig = {},
	addresses = [];

	function init() {
		loadRegions("id_locality");
	}

	$(document).ready(init);
}();
