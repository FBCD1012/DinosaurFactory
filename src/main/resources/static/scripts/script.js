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

document.addEventListener("DOMContentLoaded", function () {
    const countdownIntervals = [];

    const hatchButtons = document.querySelectorAll('.hatch-button');

    hatchButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            const index = parseInt(button.dataset.index);
            if (!countdownIntervals[index]) {
                startCountdown(button, 3 * 60 * 60); // 开始倒计时
                button.disabled = true; // 禁用按钮
            }
        });
    });

    function startCountdown(button, time) {
        const index = parseInt(button.dataset.index);
        let countdownTime = time;
        countdownIntervals[index] = setInterval(function () {
            countdownTime--;
            if (countdownTime <= 0) {
                clearInterval(countdownIntervals[index]);
                countdownIntervals[index] = null;
                sendMessage(index);
                button.disabled = false; // 完成孵化后启用按钮
            } else {
                updateButtonText(button, countdownTime);
            }
        }, 1000);

        // 倒计时开始后立即更新按钮文本
        updateButtonText(button, countdownTime);
    }

    function updateButtonText(button, time) {
        const hours = Math.floor(time / 3600);
        const minutes = Math.floor((time % 3600) / 60);
        const seconds = time % 60;
        button.textContent = `Countdown: ${hours}h ${minutes}m ${seconds}s`;
    }

    function sendMessage(index) {
        alert(`Hatch ${index + 1} completed!`);
    }
});



// 弹窗
document.addEventListener('DOMContentLoaded', function () {
    // 获取相关元素
    const confirmBtn = document.getElementById('confirmBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const modal = document.getElementById('modal');
    const closeBtn = document.getElementById('close');
    const priceInput = document.querySelector('.price-input');
    const modifyPriceBtn = document.getElementById('modifyPriceBtn');

    // 当点击竞价按钮时显示弹出框
    const bidBtns = document.querySelectorAll('.nft__bid-btn--primary');
    bidBtns.forEach(function(bidBtn) {
        bidBtn.addEventListener('click', function () {
            event.preventDefault(); // 阻止默认链接行为
            modal.style.display = 'block';

            // 查找最近的.card__item元素
            const cardItem = bidBtn.closest('.card__item');

            // 从.card__item元素中获取恐龙信息
            const dinosaurId = cardItem.querySelector('.card__nick').textContent.replace('DinosaurId:', '').trim();
            const dinosaurSex = cardItem.querySelector('.card__author:nth-child(2)').textContent.replace('DinosaurSex:', '').trim();
            const dinosaurRarity = cardItem.querySelector('.card__author:nth-child(3)').textContent.replace('DinosaurRarity:', '').trim();

            // 根据按钮状态设置确认文本
            if (bidBtn.textContent === 'Upload') {
                document.getElementById('confirm_Text').textContent = `Are you sure you want to upload this ${dinosaurRarity} ${dinosaurSex} dinosaurNFT?`;
                confirmBtn.textContent = 'Upload now';
                modifyPriceBtn.style.display = 'none'; // 隐藏确定修改按钮
            } else {
                document.getElementById('confirm_Text').textContent = `Are you sure you want to remove this ${dinosaurRarity} ${dinosaurSex} dinosaurNFT?`;
                confirmBtn.textContent = 'Remove from market';
                modifyPriceBtn.style.display = 'block'; // 显示确定修改按钮
            }
        });
    });

    // 当点击取消按钮或者关闭按钮时隐藏弹出框
    cancelBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    closeBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    // 点击确认按钮时的逻辑
    confirmBtn.addEventListener('click', function () {
        // 这里写发送信息给后端的逻辑

        // 模拟发送成功后的操作
        bidBtns.forEach(function(bidBtn) {
            if (bidBtn.textContent === 'Upload') {
                bidBtn.textContent = 'On sale';
                bidBtn.classList.remove('nft__bid-btn--primary');
                bidBtn.classList.add('nft__bid-btn--sold');
            } else {
                bidBtn.textContent = 'Upload';
                bidBtn.classList.remove('nft__bid-btn--sold');
                bidBtn.classList.add('nft__bid-btn--primary');
            }
        });
        modal.style.display = 'none';
    });

    // 点击确定修改按钮时的逻辑
    modifyPriceBtn.addEventListener('click', function () {
        const newPrice = parseFloat(priceInput.value);

        // 验证价格是否合理
        if (isNaN(newPrice) || newPrice <= 0) {
            alert('该价格不合理。');
        } else {
            // 这里应该获取当前恐龙的 ID，然后发送到后端
            alert(`修改价格为 ${newPrice} 成功`);
        }
    });

});

// 模拟向后端发送 DinosaurId 和修改后的价格的函数
function sendModifiedPriceToBackend(dinosaurId, newPrice) {
    // 这里模拟向后端发送数据的操作
    // 假设发送成功
    alert('修改成功');
}
function sendDinosaurIdToBackend(dinosaurId) {
}
//Mating点击
document.addEventListener('DOMContentLoaded', function() {
    event.preventDefault(); // 阻止默认链接行为
    const matingBtns = document.querySelectorAll('.nft__bid-btn--secondary');

    matingBtns.forEach(function(btn) {
        event.preventDefault(); // 阻止默认链接行为
        btn.addEventListener('click', function(event) {
            event.preventDefault(); // 阻止默认链接行为
            const cardItem = event.target.closest('.card__item');
            cardItem.classList.toggle('white-background');
        });
    });
});
document.addEventListener('DOMContentLoaded', function () {
    const printInfoButtons = document.querySelectorAll('.nft__bid-btn--primary');

    printInfoButtons.forEach(function(button) {
        button.addEventListener('click', function () {
            const cardItem = button.closest('.card__item');
            const dinosaurId = cardItem.querySelector('.card__nick').textContent.split(':')[1].trim();
            const dinosaurSex = cardItem.querySelector('.card__author:nth-child(2)').textContent.split(':')[1].trim();
            const dinosaurRarity = cardItem.querySelector('.card__author:nth-child(3)').textContent.split(':')[1].trim();
            const dinosaurURI = cardItem.querySelector('.card__img').src;

            // 在页面里放置信息
            document.getElementById('confirmText').innerHTML = ` 
                <img src="${dinosaurURI}" alt="Dinosaur Image" style="width: 100%; max-width: 250px; height: auto;">
                <p>Dinosaur Id: ${dinosaurId}</p>
                <p>Dinosaur Sex: ${dinosaurSex}</p>
                <p>Dinosaur Rarity: ${dinosaurRarity}</p>
               
            `;

            // 展示
            const modal = document.getElementById('modal');
            modal.style.display = 'block';
        });
    });

    // 点击按钮时关闭modal
    document.getElementById('close').addEventListener('click', function() {
        const modal = document.getElementById('modal');
        modal.style.display = 'none';
    });

    // 或者，你可能希望在用户点击模态框外部时关闭该模态框
    window.onclick = function(event) {
        const modal = document.getElementById('modal');
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    };
});
