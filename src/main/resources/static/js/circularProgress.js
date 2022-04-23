let attendedProgressBar = document.querySelector(".attended");
let absenceProgressBar = document.querySelector(".absence");

let attendedValue = document.querySelector(".attended .value-container");
let absenceValue = document.querySelector(".absence .value-container");

let speed = 20;
let progressValue = 0;

function setProgressValue(value, progressValue, progressBar) {
    let valueInt = parseInt(value.textContent);
    if (valueInt === 0) {
        value.textContent = `0%`;
    } else {
        let counter = setInterval(() => {
            progressValue++;
            value.textContent = `${progressValue}%`;
            progressBar.style.background = `conic-gradient(
          #4d5bf9 ${progressValue * 3.6}deg,
          #cadcff ${progressValue * 3.6}deg)`;
            if (progressValue === valueInt) {
                clearInterval(counter);
            }
        }, speed);
    }
}

setProgressValue(attendedValue, progressValue, attendedProgressBar);
setProgressValue(absenceValue, progressValue, absenceProgressBar);
