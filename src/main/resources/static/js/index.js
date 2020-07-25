var app = new Vue({
  el: '#app',
  data () {
    return {
      raceMessage: null,
      finishLine: 0,
      racers: [],
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
    },
    message: function () {
      if (this.raceMessage != null) {
        return this.raceMessage;
      } else if (this.over) {
        return this.lastRoll.racer.name + ' wins the race! Congratulations!!!'
      } else if (this.lastRoll.crashed) {
        return ''.concat(this.lastRoll.racer.name + ' chose ' + this.lastRoll.speedType + ' speed, ')
          .concat('and rolled ' + this.lastRoll.roll + ' and moved ' + this.lastRoll.move + '. ')
          .concat(this.lastRoll.racer.name + ' CRASHED!!! ')
          .concat(this.currentRacer.name + ' rolls next!')
      } else if (this.lastRoll.damaged && this.lastRoll.normalSpeed) {
        return ''.concat(this.lastRoll.racer.name + ' chose ' + this.lastRoll.speedType + ' speed, ')
          .concat('and rolled ' + this.lastRoll.roll + ' and moved ' + this.lastRoll.move + '. ')
          .concat(this.lastRoll.racer.name + ' has ' + this.lastRoll.newDamage + ' damage. ')
          .concat(this.currentRacer.name + ' rolls next!')
      } else if (this.lastRoll.damaged && this.lastRoll.superSpeed) {
        return ''.concat(this.lastRoll.racer.name + ' chose ' + this.lastRoll.speedType + ' speed, ')
          .concat('and rolled ' + this.lastRoll.roll + ' and moved ' + this.lastRoll.move + '. ')
          .concat(this.lastRoll.racer.name + ' now has ' + this.lastRoll.newDamage + ' damage. ')
          .concat(this.currentRacer.name + ' rolls next!')
      } else {
        return ''.concat(this.lastRoll.racer.name + ' chose ' + this.lastRoll.speedType + ' speed, ')
          .concat('and rolled ' + this.lastRoll.roll + ' and moved ' + this.lastRoll.move + '. ')
          .concat(this.currentRacer.name + ' rolls next!')
      }
    }
  },
  methods: {
    fetchRace: function () {
      axios
        .get('/api/races')
        .then(response => {
          this.raceMessage = response.data.message
          this.racers = response.data.racers
          this.finishLine = response.data.finishLine
          this.currentRacer = response.data.currentRacer
          this.lastRoll = response.data.lastRoll
          this.allCrashed = response.data.allCrashed
          this.over = response.data.over
        })
    },
    fetchSettings: function () {
      axios
        .get('/api/races/settings')
        .then(response => {
          this.testMode = response.data.testMode
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
          axios
            .get('/api/races')
            .then(response => {
              this.raceMessage = response.data.message
              this.racers = response.data.racers
              this.finishLine = response.data.finishLine
              this.currentRacer = response.data.currentRacer
              this.lastRoll = response.data.lastRoll
              this.allCrashed = response.data.allCrashed
              this.over = response.data.over

              this.processing = false
            })
        })
    },
    newRace: function () {
      this.processing = true

      axios
        .post('/api/races/new', {})
        .then(response => {
          this.loadRace()

          this.processing = false
        })
    },
    loadRace: function () {
      this.fetchSettings()
      this.fetchRace()
      this.roll = 0
    }
  },
  mounted () {
    this.loadRace()
  }
})
