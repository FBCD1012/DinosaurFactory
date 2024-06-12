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


//孵化倒计时
document.addEventListener("DOMContentLoaded", function () {
    const countdownIntervals = [];
    const hatchButtons = document.querySelectorAll('.hatch-button');

    hatchButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            const index = parseInt(button.dataset.index);
            const isHatchd = document.querySelectorAll('.card__author')[index].textContent.includes('true');
            if (!countdownIntervals[index] && isHatchd) {
                startCountdown(button, 3 * 60 * 60); // 开始倒计时
                button.disabled = true; // 禁用按钮
            }
        });
    });

    //开始倒计时
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

    //更新倒计时文本
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


// upload和remove the market的按钮打开弹窗
document.addEventListener('DOMContentLoaded', function () {
    // 获取相关元素
    const uploadBtn = document.getElementById('uploadBtn');
    const removeBtn = document.getElementById('removeBtn');
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
                document.getElementById('confirm_Text').textContent = `Are you sure you want to upload this dinosaurNFT?`;
                uploadBtn.style.display='block';
                removeBtn.style.display='none';
                modifyPriceBtn.style.display = 'none'; // 隐藏确定修改按钮
            } else {
                document.getElementById('confirm_Text').textContent = `Are you sure you want to remove from the market this dinosaurNFT?`;
                uploadBtn.style.display='none';
                removeBtn.style.display='block';
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
    uploadBtn.addEventListener('click', function () {
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
    // 点击确认按钮时的逻辑
    removeBtn.addEventListener('click', function () {
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
        axios.post('/getTheRecommendedPrice',{
            dId:dinosaurId
        },{
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function (response){
            if (isNaN(newPrice) || newPrice>response.data) {
                alert(`该价格不合理。推荐价格为:${response.data}`);
            }else {
                axios.post('/changeTheDPrice',{
                    dinosaurIds:dinosaurId,
                    changePrice:newPrice
                },{
                    headers : {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(({data}) => {
                    if (data.success) {
                        alert("upload success :)")
                    } else {
                        alert("Failed to upload the dinosaur :(")
                    }
                })
            }
        })
    });
});

//Mating点击事件
document.addEventListener("DOMContentLoaded", function() {
    var selectedCards = []; // 改为数组，用于存储选定的卡面

    // 添加点击事件监听器到所有"Mating"按钮
    var matingBtns = document.querySelectorAll(".nft__bid-btn--secondary");
    matingBtns.forEach(function(matingBtn) {
        matingBtn.addEventListener("click", function(event) {
            var cardItem = event.target.closest('.card__item');
            var dinosaurSex = cardItem.querySelector('.card__author').textContent.split(':')[1].trim();

            // 如果选定的卡面少于2个，将当前卡面加入选定的卡面数组
            if (selectedCards.length < 2) {
                selectedCards.push({
                    cardItem: cardItem,
                    dinosaurSex: dinosaurSex
                });
                cardItem.classList.toggle('white-background');
                matingBtn.classList.toggle("mating-mode");
            }

            // 如果选定的卡面等于2个，进行配对判断
            if (selectedCards.length === 2) {
                var firstDinosaurSex = selectedCards[0].dinosaurSex;
                var secondDinosaurSex = selectedCards[1].dinosaurSex;

                if (firstDinosaurSex !== secondDinosaurSex) {
                    axios.post("/breeding",{
                        DinosaurStringHash:selectedCards[0].cardItem.querySelector('.card__nick').textContent.trim(),
                        DinosaurStringTwo:selectedCards[1].cardItem.querySelector('.card__nick').textContent.trim()
                    },{
                        headers : {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    }).then(({data}) => {
                        if (data.success) {
                            alert("breeding success :)")
                        } else {
                            alert("Failed to breeding the Egg :(")
                        }
                    })
                } else {
                    alert("同性不可交配");
                }

                // 重置选定的卡面数组
                selectedCards.forEach(function(selectedCard) {
                    selectedCard.cardItem.classList.remove('white-background');
                    selectedCard.cardItem.querySelector('.nft__bid-btn--secondary').classList.remove("mating-mode");
                });
                selectedCards = [];
            }
        });
    });
});


//将获取信息插入弹窗里面
document.addEventListener('DOMContentLoaded', function () {
    const printInfoButtons = document.querySelectorAll('.nft__bid-btn--primary');

    printInfoButtons.forEach(function(button) {
        button.addEventListener('click', function () {
            const cardItem = button.closest('.card__item');
            dinosaurId = cardItem.querySelector('.card__nick').textContent.split(':')[1].trim();
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
function  uploadTheDinosaur(){
    upLoadDinosaur(dinosaurId)
}

//购买弹窗
document.addEventListener('DOMContentLoaded', function () {
    const buyButtons = document.querySelectorAll('.market__link');
    buyButtons.forEach(function(button) {
        button.addEventListener('click', function () {
            const cardItem = button.closest('.card__item');
            // 从.card__item元素中获取恐龙信息
            const dinosaurId = cardItem.querySelector('.card__nick').textContent.replace('dinosaurId:', '').trim();
            const dinosaurPricy = cardItem.querySelector('.card__author:nth-child(2)').textContent.replace('price:', '').trim();
            const dinosaurRarity = cardItem.querySelector('.card__author:nth-child(3)').textContent.replace('rarity:', '').trim();
            const dinosaurURI = cardItem.querySelector('.card__img').src;
            // 在购买弹窗里放置信息
            document.getElementById('market_confirmText').innerHTML = `
                <img src="${dinosaurURI}" alt="Dinosaur Image" style="width: 100%; max-width: 250px; height: auto;">
                <p>Dinosaur Id: ${dinosaurId}</p>
                <p>Dinosaur Pricy: ${dinosaurPricy}</p>
                <p>Dinosaur Rarity: ${dinosaurRarity}</p>
            `;
            // 展示购买弹窗
            const marketModal = document.getElementById('market_modal');
            marketModal.style.display = 'block';
        });
    });

    // 点击关闭购买弹窗
    document.getElementById('market_close').addEventListener('click', function() {
        const marketModal = document.getElementById('market_modal');
        marketModal.style.display = 'none';
    });

    // 或者，你可能希望在用户点击购买弹窗外部时关闭该弹窗
    window.onclick = function(event) {
        const marketModal = document.getElementById('market_modal');
        if (event.target === marketModal) {
            marketModal.style.display = 'none';
        }
    };


    // 确认交易按钮点击事件
    document.getElementById('market_confirmBtn').addEventListener('click', function() {
        // 用户地址
        const result = document.cookie.match(/0x[a-fA-F0-9]+/g)
        console.log('UserAddress:', result[0]);

        // 获取当前卡面的恐龙ID
        const dinosaurId = document.getElementById('market_confirmText').querySelector('p:nth-child(2)').textContent.replace('Dinosaur Id:', '').trim();
        console.log('Dinosaur ID:', dinosaurId);


        // 在这里你可以添加实际的交易逻辑，比如调用智能合约进行交易等等

        // 关闭购买弹窗
        const marketModal = document.getElementById('market_modal');
        marketModal.style.display = 'none';
    });

    // 取消交易按钮点击事件
    document.getElementById('market_closeBtn').addEventListener('click', function() {
        // 关闭购买弹窗
        const marketModal = document.getElementById('market_modal');
        marketModal.style.display = 'none';
    });
});