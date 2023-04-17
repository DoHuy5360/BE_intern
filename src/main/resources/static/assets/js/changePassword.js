document.addEventListener("DOMContentLoaded", (e) => {
	const form = document.querySelector("#change-password-form");
	const setnewPws = document.querySelector("#setnew-pws");
	const retypePws = document.querySelector("#retype-pws");
	form.addEventListener("submit", (e) => {
		e.preventDefault();
		var currentUrl = window.location.href;
		var url = new URL(currentUrl);
		const body = {
			accountPassword: setnewPws.value,
			retypeAccountPassword: retypePws.value,
		};
		const token = url.searchParams.get("certificate");
		fetch("https://be-intern.onrender.com/api/v2/account/change-password", {
			method: "PUT",
			headers: {
				Authorization: `Bearer ${token}`,
				"Content-Type": "application/json",
			},
			body: JSON.stringify(body),
		})
			.then((res) => res.json())
			.then((body) => {
				body.status === "OK" ? alert("Success") : alert("Fail");
			});
	});
});
