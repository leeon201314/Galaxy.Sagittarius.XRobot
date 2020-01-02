<template>
<div style="width: 100%;">
  <van-row>
  <van-col span="24">
    <div id="main" style="width: 100%;height: 300px;"></div>
  </van-col>
</van-row>

<van-row>
  <van-col span="24"><van-cell title="单元格" value="内容" /></van-col>
</van-row>

  <van-cell-group>
  <van-slider v-model="value" @change="onChange" />
  <van-cell title="单元格" value="内容" />
  <van-cell title="单元格" value="内容" label="描述信息" />
  <md-field-group>
	<md-field
v-model="account"
icon="username"
        placeholder="随便输"
right-icon="clear-full"
        v-validate="'required'"
        name="user"
        data-vv-as="帐号"
 @right-click="clearText"
      />
</md-field-group>
</van-cell-group>
</div>
</template>
<script>
import echarts from 'echarts'

export default {
  name: '',
  data () {
    return {
      opinionData: [{value: 15, name: '速度'}],
      opinionData2: [{value: 15, name: '转向'}],
      value: 50
    }
  },
  methods: {
    onChange (value) {
      this.$toast('当前值：' + value)
    },

    drawPie (id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          show: false
        },
        series: [
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
            data: this.opinionData
          },
          {
            name: '转速',
            type: 'gauge',
            center: ['25%', '50%'], // 默认全局居中
            radius: '50%',
            min: -30,
            max: 30,
            splitNumber: 6,
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
            data: this.opinionData2
          }
        ]
      })
    }
  },
  // 调用
  mounted () {
    this.container = this.$refs.container
    this.$nextTick(function () {
      this.drawPie('main')
    })
  }
}
</script>
<style scoped>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }
</style>
