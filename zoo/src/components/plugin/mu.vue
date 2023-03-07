<template>
    <div id="mu" :style="{width: '800px', height: '300px'}"></div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    data() {
            return {
                data: {
                    name: [],
                    data: []
                }
        }
    },
    methods: {
        getSecond() {
            this.$http.get(this.$host+'/animal/data/genus/max').then(res => {
                if (res.data.code != 200) {
                    this.$message.error('当前服务异常，请稍后再试');
                } else {
                    this.data = res.data.data;
                }
                this.buildChart()
            })
        },
        buildChart() {
            var chartDom = document.getElementById('mu');
            var myChart = echarts.init(chartDom);
            var option;

            // prettier-ignore
            let dataAxis = this.data.name
            // prettier-ignore
            let data = this.data.data
            let yMax = 500;
            let dataShadow = [];
            for (let i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
            }
            option = {
            title: {
                text: '目属物种',
                subtext: ''
            },
            xAxis: {
                data: dataAxis,
                axisLabel: {
                inside: true,
                color: '#fff'
                },
                axisTick: {
                show: false
                },
                axisLine: {
                show: false
                },
                z: 10
            },
            yAxis: {
                axisLine: {
                show: false
                },
                axisTick: {
                show: false
                },
                axisLabel: {
                color: '#999'
                }
            },
            dataZoom: [
                {
                type: 'inside'
                }
            ],
            series: [
                {
                type: 'bar',
                showBackground: true,
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    { offset: 0, color: '#83bff6' },
                    { offset: 0.5, color: '#188df0' },
                    { offset: 1, color: '#188df0' }
                    ])
                },
                emphasis: {
                    itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        { offset: 0, color: '#2378f7' },
                        { offset: 0.7, color: '#2378f7' },
                        { offset: 1, color: '#83bff6' }
                    ])
                    }
                },
                data: data
                }
            ]
            };
            // Enable data zoom when user click bar.
            const zoomSize = 6;
            myChart.on('click', function (params) {
            console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
            myChart.dispatchAction({
                type: 'dataZoom',
                startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                endValue:
                dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
            });
            });

            option && myChart.setOption(option);
        }
    },
    mounted() {
      this.getSecond()
    }
}
</script>

<style>
</style>