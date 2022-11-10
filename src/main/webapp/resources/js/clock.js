const tic = 10000


const canvas = document.getElementById('clock-canvas')
const ctx = canvas.getContext('2d')
const width = canvas.width
const height = canvas.height


function clock() {
    const date = new Date();
    const hours = ((date.getHours() + 11) % 12 + 1);
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    document.getElementById("hour").innerText = hours.toString() + ' :'
    document.getElementById("minute").innerText = minutes.toString() + ' :'

    document.getElementById("second").innerText = seconds.toString()

    drawClock(hours, minutes, seconds)
}
function drawClock(hours, minutes, seconds) {
    ctx.moveTo(width/2, height/2)
    ctx.beginPath()

    let cx = width/2
    let cy = height/2
    ctx.fillStyle = '#FFF'
    ctx.arc(cx, cy, 200, 0, 2*Math.PI)
    ctx.fill()
    ctx.fillStyle = '#000'

    ctx.beginPath()
    ctx.moveTo(width/2, height/2)
    ctx.lineWidth = 2
    let alpha = hours / 12 * 2 * Math.PI
    let hoursLen = 80
    ctx.lineTo(cx + hoursLen*Math.sin(alpha), cy - hoursLen*Math.cos(alpha))
    ctx.stroke()

    ctx.lineWidth = 1.5
    ctx.moveTo(width/2, height/2)
    alpha = minutes / 60 * 2 * Math.PI
    let minutesLen = 120
    ctx.lineTo(cx + minutesLen*Math.sin(alpha), cy - minutesLen*Math.cos(alpha))
    ctx.stroke()

    ctx.lineWidth = 1
    ctx.moveTo(width/2, height/2)
    alpha = seconds / 60 * 2 * Math.PI
    let secondsLen = 140
    ctx.lineTo(cx + secondsLen*Math.sin(alpha), cy - secondsLen*Math.cos(alpha))
    ctx.stroke()

    let pSize = 50
    ctx.lineWidth = 5

    for(let i =  0; i < 4; i++) {
        ctx.beginPath()
        let alpha = i/4*2*Math.PI
        let r = 200
        ctx.moveTo(cx + r*Math.sin(alpha)-pSize*Math.sin(alpha), cy - r*Math.cos(alpha) + pSize*Math.cos(alpha))
        ctx.lineTo(cx + r*Math.sin(alpha), cy - r*Math.cos(alpha))
        ctx.stroke()

    }
    ctx.lineWidth = 1
}
clock()
setInterval(clock, tic)