import Axios from 'axios'
var baseURL = 'http://192.168.65.127:8866'
class httpRequest {
  constructor () {
    this.options = {
      method: '',
      url: ''
    }
    // 存储请求队列
    this.queue = {}
  }
  // 销毁请求实例
  destroy (url) {
    delete this.queue[url]
    const queue = Object.keys(this.queue)
    return queue.length
  }
  // 请求拦截
  interceptors (instance, url) {
    // 添加请求拦截器
    instance.interceptors.request.use(config => {
      // 在发送请求之前做些什么
      return config
    }, error => {
      // 对请求错误做些什么
      return Promise.reject(error)
    })

    // 添加响应拦截器
    instance.interceptors.response.use(res => {
      let { data } = res
      const is = this.destroy(url)
      if (!is) {
        setTimeout(() => {
          // TODO
        }, 500)
      }
      if (!(data instanceof Blob)) {
        if (data.code && data.code !== '200') {
          // 后端服务在个别情况下回报201，待确认
          if (data.code === '401') {

          } else {

          }
          return Promise.reject(data)
        }
      }
      return Promise.resolve(data)
    }, error => {
      // 对响应错误做点什么
      return Promise.reject(error)
    })
  }
  // 创建实例
  create () {
    let conf = {
      baseURL: baseURL
    }
    return Axios.create(conf)
  }
  // 合并请求实例
  mergeReqest (instances = []) {
    //
  }
  // 请求实例
  request (options) {
    var instance = this.create()
    this.interceptors(instance, options.url)
    options = Object.assign({}, options)
    this.queue[options.url] = instance
    return instance(options)
  }
}
export default httpRequest
