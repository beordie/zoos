<template>
    <div id="chart" :style="{width: '800px', height: '600px'}"></div>
</template>

<script>
import * as echarts from 'echarts';
export default {
    data() {
            return {
                first: [],
                second: [],
                third: []
        }
    },
    methods: {
        getFirst() {
            this.$http.get(this.$host+'/animal/data/kinds').then(res => {
                if (res.data.code != 200) {
                    this.$message.error('当前服务异常，请稍后再试');
                } else {
                    this.first = res.data.data;
                }
                this.buildChart()
            })
        },
        getSecond(name) {
            this.$http.get(this.$host+'/animal/data/family?order=' + name).then(res => {
                if (res.data.code != 200) {
                    this.$message.error('当前服务异常，请稍后再试');
                } else {
                    this.second = res.data.data;
                }
                this.buildChart()
            })
        },
        getThrid(name) {
            this.$http.get(this.$host+'/animal/data/genus?family=' + name).then(res => {
                if (res.data.code != 200) {
                    this.$message.error('当前服务异常，请稍后再试');
                } else {
                    this.third = res.data.data;
                }
                this.buildChart()
            })
        },
        buildChart() {
            var chartDom = document.getElementById('chart');
            var myChart = echarts.init(chartDom);
            var option = {
                tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                data: [
                    'Direct',
                    'Marketing',
                    'Search Engine',
                    'Email',
                    'Union Ads',
                    'Video Ads',
                    'Baidu',
                    'Google',
                    'Bing',
                    'Others'
                ]
                },
                series: [
                {
                    name: 'Access From',
                    type: 'pie',
                    selectedMode: 'single',
                    radius: [0, '20%'],
                    label: {
                    position: 'inner',
                    fontSize: 14
                    },
                    labelLine: {
                    show: false
                    },
                    data: this.third
                },
                {
                    name: 'Access From',
                    type: 'pie',
                    selectedMode: 'single',
                    radius: ['25%', '40%'],
                    label: {
                    position: 'inner',
                    fontSize: 14
                    },
                    labelLine: {
                    show: false
                    },
                    data: this.second
                },
                {
                    name: 'Access From',
                    type: 'pie',
                    radius: ['45%', '60%'],
                    labelLine: {
                    length: 30
                    },
                    label: {
                    formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                    backgroundColor: '#F6F8FC',
                    borderColor: '#8C8D8E',
                    borderWidth: 1,
                    borderRadius: 4,
                    rich: {
                        a: {
                        color: '#6E7079',
                        lineHeight: 22,
                        align: 'center'
                        },
                        hr: {
                        borderColor: '#8C8D8E',
                        width: '100%',
                        borderWidth: 1,
                        height: 0
                        },
                        b: {
                        color: '#4C5058',
                        fontSize: 14,
                        fontWeight: 'bold',
                        lineHeight: 33
                        },
                        per: {
                        color: '#fff',
                        backgroundColor: '#4C5058',
                        padding: [3, 4],
                        borderRadius: 4
                        }
                    }
                    },
                    data: this.first
                }
                ]
            }
            myChart.setOption(option, true)
            const _this = this;
            myChart.on('dblclick', function (params) {
                if(params.componentIndex == 2) {
                    _this.getSecond(params.data.name)
                } else if(params.componentIndex == 1) {
                    _this.getThrid(params.data.name)
                }
            });
        }
    },
    mounted() {
        
    },
    created() {
        this.getFirst()
        this.getSecond('单孔目')
        this.getThrid('金毛鼹科')
    }
}
</script>

<style>
</style>
  