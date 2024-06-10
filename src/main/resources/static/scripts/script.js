src = "https://unpkg.com/axios/dist/axios.min.js"
// collapsible
let coll = document.getElementsByClassName("collapsible");
let i;

for (i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function () {
        this.classList.toggle("active");
        let content = this.nextElementSibling;
        if (content.style.maxHeight) {
            content.style.maxHeight = null;
        } else {
            content.style.maxHeight = content.scrollHeight + "px";
        }
    });
}

// 获取按钮元素
var walletButton = document.getElementById('walletButton');
// 声明一个变量来存储地址值
var userAddress = "";
// 检查MetaMask是否已安装
if (typeof window.ethereum !== 'undefined') {
    console.log('MetaMask is installed!');

    // 当按钮被点击时，连接到MetaMask
    walletButton.addEventListener('click', function () {
        // 请求用户授权连接到MetaMask
        ethereum.request({method: 'eth_requestAccounts'})
            .then(function (accounts) {
                // 连接成功，更新按钮显示为钱包地址的前几位
                // 只显示前6位
                walletButton.textContent = accounts[0].substring(0, 6) + '...';
                // 将地址存储在变量中
                userAddress = accounts[0];
                document.cookie = `userAddress=${userAddress}`
                postToTheAddress(userAddress)
                // 添加鼠标悬停事件
                walletButton.addEventListener('mouseover', function () {
                    walletButton.textContent = accounts[0];
                });
                // 添加鼠标移出事件
                walletButton.addEventListener('mouseout', function () {
                    walletButton.textContent = accounts[0].substring(0, 6) + '...';
                });
            })
            .catch(function (error) {
                // 连接失败，打印错误信息
                console.error(error);
            });
    });
} else {
    // MetaMask未安装，显示错误消息或者提示用户安装MetaMask
    console.log('Please install the MetaMask extension!');
}

function postToTheAddress(userAddress) {
    axios.post("/address", {
        userAdd: userAddress
    }, {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(({data}) => {
        if (data.success) {
            alert("User address obtained successfully :)")
        } else {
            alert("Failed to obtain user address :(")
        }
    })
}

//蛋的孵化
var countdownIntervals = [null, null, null];
var countdownTimes = [3 * 60 * 60, 3 * 60 * 60, 3 * 60 * 60]; // 每个按钮的倒计时时间（秒）
var hatchButtons = [
    document.getElementById("hatchButton1"),
    document.getElementById("hatchButton2"),
    document.getElementById("hatchButton3")
];

function toggleCountdown(index, event) {
    event.preventDefault(); // 阻止默认链接行为
    if (countdownIntervals[index]) {
        // 如果倒计时正在运行，停止它
        clearInterval(countdownIntervals[index]);
        countdownIntervals[index] = null;
        hatchButtons[index].textContent = "Hatch"; // 重置按钮文本
    } else {
        // 如果倒计时没有运行，启动它
        startCountdown(index);
    }
}

function startCountdown(index) {
    countdownIntervals[index] = setInterval(function () {
        countdownTimes[index]--;
        if (countdownTimes[index] <= 0) {
            clearInterval(countdownIntervals[index]);
            countdownIntervals[index] = null;
            sendMessage(index);
        } else {
            updateButtonText(index, countdownTimes[index]);
        }
    }, 1000);

    // 启动倒计时后立即更新按钮文本
    updateButtonText(index, countdownTimes[index]);
}

function updateButtonText(index, time) {
    var hours = Math.floor(time / 3600);
    var minutes = Math.floor((time % 3600) / 60);
    var seconds = time % 60;
    hatchButtons[index].textContent = "Countdown" + "：" + hours + "h " + minutes + "m " + seconds + "s";
}

// 模拟发送消息的函数
function sendMessage(index) {
    alert("孵化" + (index + 1) + "完成！");
}

// 向后端发送 DinosaurId 的函数
function sendDinosaurIdToBackend(id) {
    // 使用 fetch 发送数据给后端
    fetch('/sell/' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('后端返回的数据:', data);
            //这里添加后面的逻辑
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

document.addEventListener('DOMContentLoaded', function () {
    // 获取相关元素
    const bidBtn = document.querySelector('.nft__bid-btn--primary');
    const confirmBtn = document.getElementById('confirmBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const modal = document.getElementById('modal');
    const closeBtn = document.getElementById('close');
    const priceInput = document.querySelector('.price-input');
    const modifyPriceBtn = document.getElementById('modifyPriceBtn');

    // 模拟 DinosaurId
    let dinosaurId = 123;

    // 当点击竞价按钮时显示弹出框
    bidBtn.addEventListener('click', function () {
        event.preventDefault(); // 阻止默认链接行为
        modal.style.display = 'block';

        // 根据按钮状态设置确认文本
        if (bidBtn.textContent === 'Upload') {
            document.getElementById('confirmText').textContent = 'Are you sure you want to upload this dinosaurNFT?';
            confirmBtn.textContent = 'Upload now';
            modifyPriceBtn.style.display = 'none'; // 隐藏确定修改按钮
        } else {
            document.getElementById('confirmText').textContent = 'Are you sure you want to remove this dinosaurNFT?';
            confirmBtn.textContent = 'Remove from market';
            modifyPriceBtn.style.display = 'block'; // 显示确定修改按钮
        }
    });

    // 当点击取消按钮或者关闭按钮时隐藏弹出框
    cancelBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    closeBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    confirmBtn.addEventListener('click', function () {
        // 这里写发送信息给后端的逻辑
        if (bidBtn.textContent === 'Upload') {
            bidBtn.textContent = 'On sale';
            bidBtn.classList.remove('nft__bid-btn--primary');
            bidBtn.classList.add('nft__bid-btn--sold');
            document.getElementById('confirmText').textContent = 'Are you sure you want to upload this dinosaurNFT?';
        } else {
            bidBtn.textContent = 'Upload';
            bidBtn.classList.remove('nft__bid-btn--sold');
            bidBtn.classList.add('nft__bid-btn--primary');
            document.getElementById('confirmText').textContent = 'Are you sure you want to remove this dinosaurNFT?';
        }
        sendDinosaurIdToBackend(dinosaurId);
        modal.style.display = 'none';
    });


    // 点击确定修改按钮时的逻辑
    modifyPriceBtn.addEventListener('click', function () {
        const newPrice = parseFloat(priceInput.value);

        // 验证价格是否合理
        if (isNaN(newPrice) || newPrice <= 0) {
            alert('该价格不合理。');
        } else {
            // 向后端发送数据
            sendModifiedPriceToBackend(dinosaurId, newPrice);
        }
    });

});

// 模拟向后端发送 DinosaurId 和修改后的价格的函数
function sendModifiedPriceToBackend(dinosaurId, newPrice) {
    // 这里模拟向后端发送数据的操作
    // 假设发送成功
    alert('修改成功');
}