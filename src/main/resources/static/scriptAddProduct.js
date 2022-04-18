const image = document.getElementById("image")
const imagePreview = document.getElementById("imagePreview")

image.addEventListener("change", function (e) {
    if (e.target.files.length > 0) {
        let src = URL.createObjectURL(e.target.files[0])
        imagePreview.src = src
    }
})