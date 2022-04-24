let dragAnddrop = document.querySelector('.drag');

dragAnddrop.addEventListener('dragenter',(e)=>{
    e.preventDefault()
    dragAnddrop.classList.add('active');

});

dragAnddrop.addEventListener('dragleave' ,(e)=>{
    e.preventDefault()
    dragAnddrop.classList.remove('active');
});

dragAnddrop.addEventListener('dragover',(e)=>{
    e.preventDefault()
    dragAnddrop.classList.add('active');
});

dragAnddrop.addEventListener('drop' ,(e)=>{
    // e.preventDefault()
    dragAnddrop.classList.remove('active');
});

// ////////////////////////////////////////////////////////

let dragAnddrop2 = document.querySelector('.drag2');

dragAnddrop2.addEventListener('dragenter',(e)=>{
    e.preventDefault()
    dragAnddrop2.classList.add('active');

});

dragAnddrop2.addEventListener('dragleave' ,(e)=>{
    e.preventDefault()
    dragAnddrop2.classList.remove('active');
});

dragAnddrop2.addEventListener('dragover',(e)=>{
    e.preventDefault()
    dragAnddrop2.classList.add('active');
});

dragAnddrop2.addEventListener('drop' ,(e)=>{
    // e.preventDefault()
    dragAnddrop2.classList.remove('active');
});
// //////////////////////////////////////////////////////////
let dragAnddrop3 = document.querySelector('.drag3');

dragAnddrop3.addEventListener('dragenter',(e)=>{
    e.preventDefault()
    dragAnddrop3.classList.add('active');

});

dragAnddrop3.addEventListener('dragleave' ,(e)=>{
    e.preventDefault()
    dragAnddrop3.classList.remove('active');
});

dragAnddrop3.addEventListener('dragover',(e)=>{
    e.preventDefault()
    dragAnddrop3.classList.add('active');
});

dragAnddrop3.addEventListener('drop' ,(e)=>{

    dragAnddrop3.classList.remove('active');
});
