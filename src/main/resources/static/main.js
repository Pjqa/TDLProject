'use strict'
const idV = document.querySelector('#getId')
const itemName = document.querySelector("#getName")
const itemDescription = document.querySelector("#getDescription")
const itemCompleted = document.querySelector("#getCompleted")
const givenId = document.querySelector("#getLid")
const allToDisplay2 = document.querySelector('.box')

const printToScreenAll2 = (items) => {
    for (let item of items) {
        for (let info in item) {
            let actualText = document.createTextNode(
                `${info}: ${item[info]} `
            );
            console.log(info + ":" + item[info]);
            allToDisplay2.append(actualText)
        }
    }
}


//CREATE ITEM
function createItem() {
    let data = {
        name: itemName.value,
        description: itemDescription.value,
        completed: itemCompleted.value,
        todolist: { id: givenId.value }
    }
    console.log(data)
    fetch("http://localhost:9092/items/create", {
        method: 'post',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then((data) => console.log(`Item Created :) ${data}`))
        .catch((err) => console.log(err))
}

// READ ITEMS
const readItem = () => {
    allToDisplay2.innerHTML = "";
    fetch("http://localhost:9092/items/read")
        .then((res) => {
            (res.status !== 200) ? console.log(`There has been an error: ${res.status}`) :
                res.json()
                    .then((data) => {
                        console.log(data)
                        printToScreenAll2(data)
                    })
        }).catch((err) => console.log(err))
};


// UPDATE ITEM
function updateItem() {
    const idValue = idV.value;
    let Data = {
        name: itemName.value,
        description: itemDescription.value,
        completed: itemCompleted.value,
        todolist: { id: givenId.value }
    }
    fetch("http://localhost:9092/items/update/" + idValue, {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify(Data)
    })
        .then(res => res.json())
        .then(data => {
            console.log(`Request succeeded with JSON response ${data}`);
            console.log(data);
        })
        .catch((err) => console.log(err))
}

// DELETE ITEM
function deleteItem() {
    const idValue = idV.value;
    fetch("http://localhost:9092/items/delete/" + idValue, {
        method: 'delete',
    })
        .then((data) => {
            console.log(`Item has been deleted ${data}`);

        })
        .catch((error) => {
        });
}