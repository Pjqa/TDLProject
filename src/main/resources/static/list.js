'use strict'
const givenIdl = document.querySelector("#getLid")
const listName = document.querySelector('#getListName')
const allToDisplay = document.querySelector('.box1')


const printToScreenAll = (lists) => {
    for (let list of lists) {
        for (let info in list) {
            let actualText = document.createTextNode(
                `${info}: ${list[info]} `
            );
            console.log(info + ":" + list[info]);
            allToDisplay.append(actualText)
        }
    }
}

//CREATE LIST
function createList() {
    let data = {
        name: listName.value
    }
    console.log(data)
    fetch("http://localhost:9092/todolist/create", {
        method: 'post',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then((data) => console.log(`List Created :) ${data}`))
        .catch((err) => console.log(err))
}

// READ LIST
const readList = () => {
   allToDisplay.innerHTML = "";
    fetch("http://localhost:9092/todolist/read")
        .then((res) => {
            (res.status !== 200) ? console.log(`There has been an error: ${res.status}`) :
                res.json()
                    .then((data) => {
                        console.log(data)
                        printToScreenAll(data)
                    })
        }).catch((err) => console.log(err))
};

// UPDATE LIST
function updateList() {
    const idlVaule = givenIdl.value;
    let Data = {
        name: listName.value,
    }
    fetch("http://localhost:9092/todolist/update/" + idlVaule, {
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


// DELETE LIST
function deleteList() {
    const idlValue = givenIdl.value;
    fetch("http://localhost:9092/todolist/delete/" + idlValue, {
        method: 'delete',
    })
        .then((data) => {
            console.log(`List has been deleted ${data}`);

        })
        .catch((error) => {
        });
}