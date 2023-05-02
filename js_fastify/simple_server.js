const fastify = require('fastify')({ logger: false })

fastify.get('/', async (request, reply) => {
  return 'Hello, People'
})

const start = async () => {
  try {
    await fastify.listen({ port: 8080 , host: '0.0.0.0'})
  } catch (err) {
    fastify.log.error(err)
    process.exit(1)
  }
}
start()