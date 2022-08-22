const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = (app) => {
	app.use(
		createProxyMiddleware('/api', {
			target: 'http://localhost:서버에서 사용할 포트',
			changeOrigin: true,
		})
	);
};