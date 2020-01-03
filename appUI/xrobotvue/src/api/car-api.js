import axios from '@/libs/api.request'

// 设置转向角度
export const setSteeringAngle = (angle) => {
  return axios.request({
    url: '/carServo/setangle/' + angle,
    method: 'get'
  })
}
