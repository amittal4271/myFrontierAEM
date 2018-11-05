var smartystreets_key = '654859e6-99fa-3474-4a6e-466d363d9287';

$(function() {	
	jQuery.LiveAddress({
		key: smartystreets_key,       
		target: "US",
		autocomplete: 0,
		addresses: [{
			id: 'addr',
			address1: '#id_address',
			address2: '#id_address2',
			locality: '#id_city',
			administrative_area: '#id_locality',
			postal_code: '#id_postal_code',
			country: '#id_country'
		},{
			id: 'taxpayer',
			address1: '#id_mailing-address',
			address2: '#id_mailing-address2',
			locality: '#id_mailing-city',
			administrative_area: '#id_mailing-locality',
			postal_code: '#id_mailing-postal_code',
			country: '#id_mailing-country'
		},{
			id: 'shipping',
			address1: '#id_shipping-address',
			address2: '#id_shipping-address2',
			locality: '#id_shipping-city',
			administrative_area: '#id_shipping-locality',
			postal_code: '#id_shipping-postal_code',
			country: '#id_shipping-country'
		},{
			id: 'billing',
			address1: '#id_billing-address',
			address2: '#id_billing-address2',
			locality: '#id_billing-city',
			administrative_area: '#id_billing-locality',
			postal_code: '#id_billing-postal_code',
			country: '#id_billing-country'
		},{
			id: 'contact',
			address1: '#id_contact-address',
			address2: '#id_contact-address2',
			locality: '#id_contact-city',
			administrative_area: '#id_contact-locality',
			postal_code: '#id_contact-postal_code',
			country: '#id_contact-country'
		}]
	});
	$("#id_country").change();
	$("#id_mailing-country").change();
	$("#id_shipping-country").change();
	$("#id_billing-country").change();
	$("#id_contact-country").change();
});