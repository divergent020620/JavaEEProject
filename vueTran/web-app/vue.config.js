// vue.config.js

module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080',  // 将请求代理到网关的地址
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''  // 重写请求路径，去掉/api前缀
                }
            }
        }
    }
};
