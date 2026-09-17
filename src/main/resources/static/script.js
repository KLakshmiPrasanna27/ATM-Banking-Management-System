function login() {

    let cardNumber =
        document.getElementById("cardNumber").value;

    let pin =
        document.getElementById("pin").value;

    fetch("/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            cardNumber: cardNumber,
            pin: pin
        })

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Invalid card number or PIN");
        }

        return response.json();

    })
    .then(data => {

        localStorage.setItem(
            "accountId",
            data.accountId
        );

        localStorage.setItem(
            "customerName",
            data.customerName
        );

        localStorage.setItem(
            "cardNumber",
            data.cardNumber
        );

        localStorage.setItem(
            "balance",
            data.balance
        );

        window.location.href = "dashboard.html";

    })
    .catch(error => {

        alert(error.message);

    });
}