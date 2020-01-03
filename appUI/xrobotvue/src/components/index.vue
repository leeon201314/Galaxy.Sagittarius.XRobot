<template>
<div>
  <div id="main" style="width: 100%;height: 300px;"></div>
    <van-row>
      <van-col class="goods-title" span="6">转向</van-col>
      <van-col span="16"><van-slider :min="minSteeringAngle" :max="maxSteeringAngle" v-model="steeringAngle[0].value" @change="onSteeringAngleChange" /></van-col>
    </van-row>
    <van-row style="margin-top:30px;">
      <van-col class="goods-title" span="6">速度</van-col>
      <van-col span="16"><van-slider :min="minSpeed" :max="maxSpeed" v-model="speed[0].value" @change="onSpeedChange" /></van-col>
    </van-row>

    <van-radio-group v-model="direction">
        <van-row style="margin-top:30px;">
          <van-col span="6" offset="3"><van-radio name="1">前进</van-radio></van-col>
          <van-col span="6"><van-radio name="2">停止</van-radio></van-col>
          <van-col span="6"><van-radio name="3">后退</van-radio></van-col>
        </van-row>
    </van-radio-group>
</div>
</template>

<script>

import echarts from 'echarts'
import { setSteeringAngle } from '@/api/car-api.js'

export default {
  name: 'index',
  data () {
    return {
      speed: [{value: 90, name: '速度'}],
      steeringAngle: [{value: 15, name: '转向'}],
      minSteeringAngle: -20,
      maxSteeringAngle: 20,
      minSpeed: 0,
      maxSpeed: 120,
      charts: null,
      direction: '2'
    }
  },
  methods: {
    onSteeringAngleChange (value) {
      setSteeringAngle(value).then(res => {
        // res是一个Obj类型，用[]包装成数组
        console.log(res)
      })
    },

    onSpeedChange (value) {

    },

    setOption (charts) {
      charts.setOption({
        tooltip: {
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          show: false
        },
        series: [
          {
            name: '转向',
            type: 'gauge',
            center: ['25%', '50%'], // 默认全局居中
            radius: '50%',
            min: -20,
            max: 20,
            splitNumber: 5,
            axisLine: { // 坐标轴线
              lineStyle: { // 属性lineStyle控制线条样式
                width: 8
              }
            },
            axisTick: { // 坐标轴小标记
              length: 12, // 属性length控制线长
              lineStyle: { // 属性lineStyle控制线条样式
                color: 'auto'
              }
            },
            splitLine: { // 分隔线
              length: 20, // 属性length控制线长
              lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                color: 'auto'
              }
            },
            pointer: {
              width: 5
            },
            title: {
              offsetCenter: [0, '-30%'] // x, y，单位px
            },
            detail: {
              // 其余属性默认使用全局文本样式，详见TEXTSTYLE
              fontWeight: 'bolder'
            },
            data: this.steeringAngle
          },
          {
            name: '速度',
            type: 'gauge',
            z: 3,
            min: 0,
            max: 120,
            splitNumber: 6,
            center: ['75%', '50%'], // 默认全局居中
            radius: '50%',
            axisLine: { // 坐标轴线
              lineStyle: { // 属性lineStyle控制线条样式
                width: 10
              }
            },
            axisTick: { // 坐标轴小标记
              length: 15, // 属性length控制线长
              lineStyle: { // 属性lineStyle控制线条样式
                color: 'auto'
              }
            },
            splitLine: { // 分隔线
              length: 20, // 属性length控制线长
              lineStyle: { // 属性lineStyle（详见lineStyle）控制线条样式
                color: 'auto'
              }
            },
            data: this.speed
          }
        ]
      })
    },

    drawPie (id) {
      this.charts = echarts.init(document.getElementById(id))
      this.setOption(this.charts)
    }
  },

  // 调用
  mounted () {
    this.container = this.$refs.container
    this.$nextTick(function () {
      this.drawPie('main')
    })
  },
  watch: {
    // 观察data的变化
    steeringAngle: {
      handler (newVal, oldVal) {
        if (this.charts) {
          this.setOption(this.charts)
        }
      },
      deep: true
    },
    speed: {
      handler (newVal, oldVal) {
        if (this.charts) {
          this.setOption(this.charts)
        }
      },
      deep: true
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.goods-title {
    font-size: 14px;
    text-align:center;
    line-height:22px;
    margin-top: -8px;
  }
.goods-price {
    color: #f44;
  }
</style>
