import axios from '@/libs/api.request'

// 设置转向角度
export const setSteeringAngle = (angle) => {
  return axios.request({
    url: '/carServo/setangle/' + angle,
    method: 'get'
  })
}

// 前进
export const go = (speed) => {
  return axios.request({
    url: '/carMotor/go/' + speed,
    method: 'get'
  })
}

// 后退
export const back = (speed) => {
  return axios.request({
    url: '/carMotor/back/' + speed,
    method: 'get'
  })
}

// 停止
export const stop = () => {
  return axios.request({
    url: '/carMotor/stop',
    method: 'get'
  })
}
