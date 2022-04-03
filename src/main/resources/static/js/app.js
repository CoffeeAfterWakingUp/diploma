const togglebtn = document.getElementById('toggle-theme-btn');
const toggleimg = document.getElementById('toggle-theme-image');
const logoimg = document.getElementById('logo');

togglebtn.addEventListener('click',()=>{
    if(document.body.classList.contains('dark')){
        document.body.classList.remove('dark')
        toggleimg.src = 'img/moon.png'
        logoimg.src = 'img/logo/logo-white.jpg'
        localStorage.theme = 'light'
    }else{
        document.body.classList.add('dark')
        toggleimg.src = 'img/sun.png'
        logoimg.src = 'img/logo/logo-black.jpg'
        localStorage.theme = 'dark'
    }
})

if(localStorage.theme === 'dark'){
    document.body.classList.add('dark')
    toggleimg.src = 'img/sun.png'
    logoimg.src = 'img/logo/logo-black.jpg'
}
