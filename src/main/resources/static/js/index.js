var app = new Vue({
  el: '#app',
  data () {
    return {
      finishLine: 0,
      racers: [],
      message: null,
      currentRacer: null,
      lastRoll: null,
      allCrashed: false,
      over: false,
      testMode: false,
      roll: 0,
      processing: false
    }
  },
  computed: {
    track: function () {
      return (this.finishLine - 1)
    },
    ready: function () {
      return (this.racers.length > 0)
    }
  },
  methods: {
    fetchRace: function () {
      axios
        .get('/api/races')
        .then(response => {
          this.racers = response.data.racers
          this.finishLine = response.data.finishLine
          this.message = response.data.message
          this.currentRacer = response.data.currentRacer
          this.lastRoll = response.data.lastRoll
          this.allCrashed = response.data.allCrashed
          this.over = response.data.over

          this.roll = 0

          this.processing = false
        })
    },
    fetchSettings: function () {
      axios
        .get('/api/races/settings')
        .then(response => {
          this.testMode = response.data.testMode

          this.processing = false
        })
    },
    rollNormalSpeed: function (event) {
      this.doRoll('NORMAL')
    },
    rollSuperSpeed: function (event) {
      this.doRoll('SUPER')
    },
    doRoll: function (speedType) {
      this.processing = true

      axios
        .post('/api/races/roll', {
          'speedType': speedType,
          'roll': this.roll
        })
        .then(response => {
          this.fetchRace()
        })
    },
    newRace: function () {
      this.processing = true

      axios
        .post('/api/races/new', {})
        .then(response => {
          this.fetchRace()
        })
    }
  },
  mounted () {
    this.fetchSettings()
    this.fetchRace()
  }
})
