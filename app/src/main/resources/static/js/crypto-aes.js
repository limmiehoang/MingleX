function toWordArray(str) {
	return CryptoJS.enc.Utf8.parse(str);
}

function toString(words) {
	return CryptoJS.enc.Utf8.stringify(words);
}

function toBase64String(words) {
	return CryptoJS.enc.Base64.stringify(words);
}

function encrypt(input, key) {

	var secret_key = CryptoJS.SHA1(key);
	var iv = CryptoJS.lib.WordArray.random(16);
	var body = CryptoJS.AES.encrypt(input, secret_key, {
		iv : iv,
		mode : CryptoJS.mode.CBC
	});

	// The packet: IV + BODY
	iv.concat(body.ciphertext)

	// encode in base64
	return toBase64String(iv);
}

function decrypt(input, key) {
	// convert payload encoded in base64 to words
	var packet = CryptoJS.enc.Base64.parse(input);

	// helpers to compute for offsets
	var secret_key = CryptoJS.SHA1(key);
	var iv = CryptoJS.lib.WordArray.random(16);

	// compute for offsets
	var packet_size = packet.words.length - (iv.words.length);
	var start = iv.words.length;
	var end = packet.words.length;

	var ciphertext = CryptoJS.lib.WordArray.create(packet.words.slice(start,
			end));
	var parsed_iv = CryptoJS.lib.WordArray.create(packet.words.slice(0,
			iv.words.length));
	ciphertext = toBase64String(ciphertext);
	var decrypted = CryptoJS.AES.decrypt(ciphertext, secret_key, {
		iv : parsed_iv,
		mode : CryptoJS.mode.CBC
	});

	return toString(decrypted);
}