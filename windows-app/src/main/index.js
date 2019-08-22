'use strict'

import {app, BrowserWindow, ipcMain} from 'electron'

/**
 * Set `__static` path to static files in production
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-static-assets.html
 */
if (process.env.NODE_ENV !== 'development') {
  global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:9080`
  : `file://${__dirname}/index.html`

function createWindow () {
  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 700,
    useContentSize: true,
    width: 1130,
    frame: false,
    transparent: true,
    devTools: false,
    fullscreen: false,
    maximizable: true
    // kiosk: true
    // titleBarStyle: 'hiddenInset',
  })
  // mainWindow.setIgnoreMouseEvents(true)

  mainWindow.loadURL(winURL)
  mainWindow.on('closed', () => {
    mainWindow = null
  })
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})

const ipc = ipcMain
// 登录窗口最小化
ipc.on('window-min', function () {
  mainWindow.minimize()
})
// 登录窗口最大化
ipc.on('window-max', function () {
  if (mainWindow.isMaximized()) {
    mainWindow.restore()
  } else {
    // mainWindow.maximize()
    mainWindow.setFullScreen(true)
  }
})
ipc.on('window-close', function () {
  mainWindow.close()
})
